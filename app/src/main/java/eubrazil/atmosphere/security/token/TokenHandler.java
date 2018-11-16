package eubrazil.atmosphere.security.token;

import static java.util.stream.Collectors.toSet;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import eubrazil.atmosphere.security.SecurityConstants;
import eubrazil.atmosphere.security.exception.TokenExpiredException;
import eubrazil.atmosphere.security.exception.TokenSignatureNotVerifiedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.text.ParseException;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * The TokenHandler class.
 *
 * @author Chris Paraskeva
 * @author George Tsiolis
 */
public final class TokenHandler {

  private static final Logger LOGGER = LogManager.getLogger(TokenHandler.class);
  private static final String ROLES_DELIMITER = ",";
  private static final String CLAIM_ROLE_NAME = "role";
  private static final String ROLE_PREFIX = "ROLE_";
  private final String signerSecret;

  public TokenHandler(String signerSecret) {
    this.signerSecret = signerSecret;
  }

  /**
   * Returns a UserAuthentication object containing the principal and the role
   * of the authenticated user.
   *
   * @param token The encrypted token to be parsed
   * @return UserAuthentication object
   */
  public UsernamePasswordAuthenticationToken parseUserFromToken(String token) throws TokenExpiredException {
    LOGGER.trace("Trying to create spring authentication user by token");
    // Trim token prefix from token, probably "Bearer_"
    token = token.substring(SecurityConstants.TOKEN_PREFIX.length(), token.length());
    try {
      // Parse JWT
      SignedJWT jwt = TokenUtil.parseSignedJwt(token, signerSecret);
      // Check if the token has not expired yet
      if (Date.from(Instant.ofEpochMilli(System.currentTimeMillis())).getTime() < jwt.getJWTClaimsSet().getExpirationTime().getTime()) {
        // Set the roles of current user
        Set<GrantedAuthority> roles = Arrays.stream(((String) jwt.getJWTClaimsSet().getClaim(CLAIM_ROLE_NAME)).split(ROLES_DELIMITER)).map(SimpleGrantedAuthority::new).collect(toSet());
        // Token is not expired, return valid Authentication
        return new UsernamePasswordAuthenticationToken(jwt.getJWTClaimsSet().getSubject(), "", roles);
      } else {
        LOGGER.debug(String.format("Token has been expired for user: %s", jwt.getJWTClaimsSet().getSubject()));
        throw new TokenExpiredException();
      }
    } catch (ParseException | JOSEException | TokenSignatureNotVerifiedException ex) {
      // TODO: Proper handle of exceptions especially 'TokenSignatureNotVerifiedException'
      LOGGER.error(ex.getMessage());
    }
    return null;
  }

  /**
   * Creates a signed & encrypted JWT.
   *
   * @param user The user object which the ClaimSet will be build on
   * @return An encrypted JWT for the specific user
   */
  public String createTokenForUser(User user) {
    // Try to create a JWT for specific user
    try {
      LOGGER.debug(String.format("Trying to create JWT for user: %s and role(s): %s", user.getUsername(), user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(ROLES_DELIMITER))));
      // Prepare JWT with claims set
      JWTClaimsSet jwtClaims = new JWTClaimsSet.Builder()
          .subject(user.getUsername())
          .issueTime(new Date())
          .issuer(SecurityConstants.TOKEN_ISSUER)
          .jwtID(UUID.randomUUID().toString())
          .expirationTime(Date.from(Instant.ofEpochMilli(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION_TIME)))
          .claim(CLAIM_ROLE_NAME, user.getAuthorities().stream().map(auth -> ROLE_PREFIX.concat(auth.getAuthority())).collect(Collectors.joining(ROLES_DELIMITER))).build();
      return TokenUtil.createSignedToken(signerSecret, jwtClaims);
    } catch (JOSEException ex) {
      LOGGER.error(ex.getMessage());
    }
    return null;
  }

}

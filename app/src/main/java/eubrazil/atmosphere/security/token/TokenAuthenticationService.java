package eubrazil.atmosphere.security.token;

import eubrazil.atmosphere.security.SecurityConstants;
import eubrazil.atmosphere.security.exception.TokenExpiredException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class TokenAuthenticationService {

  private static final Logger LOGGER = LogManager.getLogger(TokenAuthenticationService.class);
  private final TokenHandler tokenHandler;

  @Autowired
  public TokenAuthenticationService(@Value("${token.signer.secret}") String secret) {
    tokenHandler = new TokenHandler(secret);
  }

  public void addAuthentication(HttpServletResponse response, UserDetails authenticatedUser) {
    response.addHeader(SecurityConstants.AUTHORIZATION_HEADER, SecurityConstants.TOKEN_PREFIX.concat(tokenHandler.createTokenForUser(new User(authenticatedUser.getUsername(), "", authenticatedUser.getAuthorities()))));
  }

  public String createToken(Authentication authenticatedUser) {
    return SecurityConstants.TOKEN_PREFIX.concat(tokenHandler.createTokenForUser(new User(authenticatedUser.getName(), "", authenticatedUser.getAuthorities())));
  }

  public Authentication getAuthentication(HttpServletRequest request) throws TokenExpiredException {
    String token = request.getHeader(SecurityConstants.AUTHORIZATION_HEADER);
    // If token is invalid return null
    if (token == null) {
      LOGGER.trace("Token does not exist");
      return null;
    } else if (!token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
      LOGGER.trace("Invalid token");
      return null;
    }
    // Return user authentication
    return tokenHandler.parseUserFromToken(token);
  }

}

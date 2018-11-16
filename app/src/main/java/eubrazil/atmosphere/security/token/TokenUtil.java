package eubrazil.atmosphere.security.token;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import eubrazil.atmosphere.security.exception.TokenSignatureNotVerifiedException;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;

public class TokenUtil {

  public static String createSignedToken(String signerSecret, JWTClaimsSet jwtClaims) throws JOSEException {
    // Create a new signer
    JWSSigner signer = new MACSigner(signerSecret);
    SignedJWT signedJwt = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), jwtClaims);
    // Sign
    signedJwt.sign(signer);
    return signedJwt.serialize();
  }

  public static String createEncryptedToken(String signerSecret, RSAPublicKey publicKey, JWTClaimsSet jwtClaims) throws JOSEException {
    // Create a new signer
    JWSSigner signer = new MACSigner(signerSecret);
    // Request JWT encrypted with RSA-OAEP and 128-bit AES/GCM
    JWEHeader header = new JWEHeader(JWEAlgorithm.RSA_OAEP, EncryptionMethod.A128GCM);
    SignedJWT signedJwt = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), jwtClaims);
    // Sign
    signedJwt.sign(signer);
    JWEObject jweObject = new JWEObject(header, new Payload(signedJwt));
    return encryptedToken(jweObject, publicKey).serialize();
  }

  private static JWEObject encryptedToken(JWEObject jwt, RSAPublicKey publicKey) throws JOSEException {
    // Create an encrypter with the specified public RSA key
    RSAEncrypter encrypter = new RSAEncrypter(publicKey);
    jwt.encrypt(encrypter);
    return jwt;
  }

  /**
   * Parses encrypted jwt.
   * @param jwtToken   The encrypted JWT
   * @param privateKey An RSAPrivateKey which is used to decrypt an encrypted
   *                   JWT
   */
  public static SignedJWT parseEncryptedJwt(String jwtToken, String signerKey, RSAPrivateKey privateKey) throws ParseException, JOSEException, TokenSignatureNotVerifiedException {
    JWEObject jweObject = JWEObject.parse(jwtToken);
    // Create a decrypter with the specified private RSA key
    RSADecrypter decrypter = new RSADecrypter(privateKey);
    // Decrypt
    jweObject.decrypt(decrypter);
    // Extract payload
    SignedJWT signedJwt = jweObject.getPayload().toSignedJWT();
    // Verify
    boolean isVerified = signedJwt.verify(new MACVerifier(signerKey));
    if (isVerified) {
      return signedJwt;
    }
    throw new TokenSignatureNotVerifiedException("Could not verify token signature");
  }

  public static SignedJWT parseSignedJwt(String jwtToken, String signerKey) throws ParseException, JOSEException, TokenSignatureNotVerifiedException {
    // Extract payload
    SignedJWT signedJwt = SignedJWT.parse(jwtToken);
    // Verify
    boolean isVerified = signedJwt.verify(new MACVerifier(signerKey));
    if (isVerified) {
      return signedJwt;
    }
    throw new TokenSignatureNotVerifiedException("Could not verify token signature");
  }

}

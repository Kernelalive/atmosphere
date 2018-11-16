package eubrazil.atmosphere.security;

/**
 * Created by kontoe on 14/12/2017.
 */
public class SecurityConstants {

  public static final long TOKEN_EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 10; // 10 days
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String TOKEN_ISSUER = "App";
  public static final String AUTHORIZATION_HEADER = "Authorization";

}

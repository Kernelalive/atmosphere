package eubrazil.atmosphere.security.exception;

import org.springframework.security.core.AuthenticationException;

public class UserNotVerifiedException extends AuthenticationException {

  public UserNotVerifiedException(String username) {
    super(String.format("User %s is not verified", username));
  }

}
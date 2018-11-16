package eubrazil.atmosphere.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UserNotFoundException extends Exception {

  @Override
  public String getMessage() {
    return "User not found";
  }

}

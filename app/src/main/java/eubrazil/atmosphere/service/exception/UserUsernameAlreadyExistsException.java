package eubrazil.atmosphere.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserUsernameAlreadyExistsException extends Exception {

  private final String username;

  public UserUsernameAlreadyExistsException(String username) {
    this.username = username;
  }

  @Override
  public String getMessage() {
    return String.format("Username %s already exists", username);
  }

}

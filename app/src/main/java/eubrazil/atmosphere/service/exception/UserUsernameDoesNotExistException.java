package eubrazil.atmosphere.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserUsernameDoesNotExistException extends Exception {

  private final String username;

  public UserUsernameDoesNotExistException(String username) {
    this.username = username;
  }

  @Override
  public String getMessage() {
    return String.format("User with username %s does not exist", username);
  }

}

package eubrazil.atmosphere.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class UserWeakPasswordException extends Exception {

  private final int minimumCharacters;

  public UserWeakPasswordException(int minimumCharacters) {
    this.minimumCharacters = minimumCharacters;
  }

  @Override
  public String getMessage() {
    return String.format("Password must be at least %d characters", minimumCharacters);
  }

}

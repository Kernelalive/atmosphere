package eubrazil.atmosphere.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by parthenis on 14/11/2017.
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserEmailAlreadyExistsException extends Exception {

  private final String email;

  public UserEmailAlreadyExistsException(String email) {
    this.email = email;
  }

  @Override
  public String getMessage() {
    return String.format("Email %s already exists", email);
  }

}

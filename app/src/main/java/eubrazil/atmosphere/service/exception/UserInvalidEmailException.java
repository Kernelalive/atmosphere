package eubrazil.atmosphere.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class UserInvalidEmailException extends Exception {

  @Override
  public String getMessage() {
    return "Invalid email";
  }

}

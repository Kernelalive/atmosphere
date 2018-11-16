package eubrazil.atmosphere.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class UserOldPasswordDoesNotMatchException extends Exception {

  @Override
  public String getMessage() {
    return "Old password does not match";
  }

}

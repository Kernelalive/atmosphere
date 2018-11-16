package eubrazil.atmosphere.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class UserMatchingPasswordDoesNotMatchException extends Exception {

  @Override
  public String getMessage() {
    return "Password does not match with matching password";
  }

}

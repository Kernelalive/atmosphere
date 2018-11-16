package eubrazil.atmosphere.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by kontoe on 27/2/2018.
 */
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class CustomDisableException extends Exception {

  @Override
  public String getMessage() {
    return "User is disabled";
  }

  @Override
  public String getLocalizedMessage() {
    return "auth.exception.userIsDisabled";
  }

}

package eubrazil.atmosphere.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class TokenExpiredException extends Exception {

  @Override
  public String getMessage() {
    return "Token has expired";
  }

}

package eubrazil.atmosphere.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class LegalgroundDoesNotExistException extends Exception {

  public LegalgroundDoesNotExistException() {
  }

  @Override
  public String getMessage() {
    return String.format("Legal ground does not exist");
  }

}

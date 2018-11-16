package eubrazil.atmosphere.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProcessingActivityDoesNotExistException extends Exception {

  public ProcessingActivityDoesNotExistException() {
  }

  @Override
  public String getMessage() {
    return String.format("Processing Activity does not exist");
  }

}

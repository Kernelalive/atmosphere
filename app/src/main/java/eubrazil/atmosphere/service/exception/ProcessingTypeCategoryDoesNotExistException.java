package eubrazil.atmosphere.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProcessingTypeCategoryDoesNotExistException extends Exception {

  public ProcessingTypeCategoryDoesNotExistException() {
  }

  @Override
  public String getMessage() {
    return String.format("Processing Type Category does not exist");
  }

}

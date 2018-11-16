package eubrazil.atmosphere.rest.transfer;

/**
 * Created by kontoe on 18/12/2017.
 */
public class ExceptionResource {

  private String message;

  public ExceptionResource(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}

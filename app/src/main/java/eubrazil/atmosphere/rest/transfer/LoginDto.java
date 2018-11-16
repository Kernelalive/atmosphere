package eubrazil.atmosphere.rest.transfer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by kontoe on 14/12/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginDto {

  private String username;
  private String password;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}

package eubrazil.atmosphere.rest.transfer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eubrazil.atmosphere.model.User;

/**
 * Transfer Object for User registration.
 * @author kontoe
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterDto {

  @JsonProperty(value = "fullName")
  private String fullName;
  @JsonProperty(value = "password")
  private String password;
  @JsonProperty(value = "email")
  private String email;
  @JsonProperty(value = "username")
  private String username;

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public User toUser() {
    User user = new User();
    user.setEmail(this.email);
    user.setUsername(this.username);
    user.setFullName(this.fullName);
    user.setPassword(this.password);
    return user;
  }

}

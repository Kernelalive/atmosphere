package eubrazil.atmosphere.rest.transfer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Transfer Object for Password (first use for changing password).
 * @author kontoe
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PasswordDto {

  @JsonProperty(value = "oldPassword")
  private String oldPassword;
  @JsonProperty(value = "newPassword")
  private String newPassword;
  @JsonProperty(value = "matchingPassword")
  private String matchingPassword;

  public String getOldPassword() {
    return oldPassword;
  }

  public void setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
  }

  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }

  public String getMatchingPassword() {
    return matchingPassword;
  }

  public void setMatchingPassword(String matchingPassword) {
    this.matchingPassword = matchingPassword;
  }

}

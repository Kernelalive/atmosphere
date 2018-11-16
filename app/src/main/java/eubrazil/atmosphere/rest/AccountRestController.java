package eubrazil.atmosphere.rest;

import eubrazil.atmosphere.model.User;
import eubrazil.atmosphere.rest.transfer.PasswordDto;
import eubrazil.atmosphere.security.authentication.AuthenticationService;
import eubrazil.atmosphere.security.exception.UserNotFoundException;
import eubrazil.atmosphere.service.UserService;
import eubrazil.atmosphere.service.exception.UserMatchingPasswordDoesNotMatchException;
import eubrazil.atmosphere.service.exception.UserOldPasswordDoesNotMatchException;
import eubrazil.atmosphere.service.exception.UserUsernameDoesNotExistException;
import eubrazil.atmosphere.service.exception.UserWeakPasswordException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Account service. All requests are based on token")
@RestController
@RequestMapping(value = "/api/v1/account", produces = "application/json")
public class AccountRestController {

  private static final Logger LOGGER = LogManager.getLogger(AccountRestController.class);
  @Autowired
  private AuthenticationService authenticationService;
  @Autowired
  private UserService<User> userService;

  /* DELETE ACCOUNT */
  @ApiOperation(value = "Delete user")
  @RequestMapping(method = RequestMethod.DELETE)
  public HttpEntity delete() {
    // TODO implement delete account
    return new ResponseEntity(HttpStatus.OK);
  }

  /* PASSWORD */
  @ApiOperation(value = "Update user password")
  @RequestMapping(value = "/password", method = RequestMethod.PUT)
  public HttpEntity password(@RequestBody PasswordDto passwordDto) throws UserOldPasswordDoesNotMatchException, UserMatchingPasswordDoesNotMatchException, UserWeakPasswordException, UserNotFoundException {
    try {
      userService.updatePassword(authenticationService.getCurrentUserName(), passwordDto.getOldPassword(), passwordDto.getNewPassword(), passwordDto.getMatchingPassword());
    } catch (UserUsernameDoesNotExistException ex) {
      // if user not found that means that user does not exist anymore
      throw new UserNotFoundException();
    }
    // TODO send email if password change
    return new ResponseEntity(HttpStatus.OK);
  }

  /* PROFILE */
  @ApiOperation(value = "Update user profile")
  @RequestMapping(value = "/profile", method = RequestMethod.PUT)
  public HttpEntity edit(@RequestBody User user) throws UserNotFoundException {
    try {
      userService.updateProfile(authenticationService.getCurrentUserName(), user);
    } catch (UserUsernameDoesNotExistException ex) {
      // if user not found that means that user does not exist anymore
      throw new UserNotFoundException();
    }
    return new ResponseEntity(HttpStatus.OK);
  }

}

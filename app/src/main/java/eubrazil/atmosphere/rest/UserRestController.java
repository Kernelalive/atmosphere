package eubrazil.atmosphere.rest;

import eubrazil.atmosphere.model.Role;
import eubrazil.atmosphere.model.User;
import eubrazil.atmosphere.service.UserService;
import eubrazil.atmosphere.service.exception.UserUsernameDoesNotExistException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "User service management")
@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

  @Autowired
  private UserService<User> userService;

  @RequestMapping(value = "{username}/enable", method = RequestMethod.PUT)
  public HttpEntity enableUser(@PathVariable("username") String username) throws UserUsernameDoesNotExistException {
    userService.enableUser(username);
    return new ResponseEntity(HttpStatus.OK);
  }

  @RequestMapping(value = "{username}/disable", method = RequestMethod.PUT)
  public HttpEntity disableUser(@PathVariable("username") String username) throws UserUsernameDoesNotExistException {
    userService.disableUser(username);
    return new ResponseEntity(HttpStatus.OK);
  }

  /* Role */
  @ApiOperation(value = "Update user role")
  @RequestMapping(value = "{username}/role/{role}", method = RequestMethod.PUT)
  public HttpEntity disableUser(@PathVariable("username") String username, @PathVariable("role") String role) throws UserUsernameDoesNotExistException {
    userService.updateRole(username, Role.valueOf(role));
    return new ResponseEntity(HttpStatus.OK);
  }

}

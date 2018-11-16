package eubrazil.atmosphere.rest;

import eubrazil.atmosphere.model.User;
import eubrazil.atmosphere.rest.transfer.ExceptionResource;
import eubrazil.atmosphere.rest.transfer.LoginDto;
import eubrazil.atmosphere.rest.transfer.RegisterDto;
import eubrazil.atmosphere.security.SecurityConstants;
import eubrazil.atmosphere.security.exception.CustomBadCredentialsException;
import eubrazil.atmosphere.security.exception.CustomDisableException;
import eubrazil.atmosphere.security.token.TokenAuthenticationService;
import eubrazil.atmosphere.service.UserService;
import eubrazil.atmosphere.service.exception.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Authorization service")
@RestController
@RequestMapping(value = "/api/v1/auth", produces = "application/json")
public class AuthenticationRestController {

  private static final Logger LOGGER = LogManager.getLogger(AuthenticationRestController.class);
  @Autowired
  private UserService<User> userService;
  @Autowired
  private TokenAuthenticationService tokenAuthenticationService;
  @Autowired
  private AuthenticationManager authenticationManager;

  @ApiOperation(value = "Register user")
  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public HttpEntity create(@RequestBody RegisterDto registerDto) throws UserUsernameDoesNotExistException, UserInvalidEmailException, UserUsernameTooShortException, UserUsernameAlreadyExistsException, UserEmailAlreadyExistsException, UserWeakPasswordException, UserInvalidUsernameException {
    User user = registerDto.toUser();
    userService.register(user);
    return new ResponseEntity(HttpStatus.OK);
  }

  @ApiOperation(value = "Get token")
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public HttpEntity create(@RequestBody LoginDto loginDto) throws CustomBadCredentialsException, CustomDisableException {
    try {
      UsernamePasswordAuthenticationToken authenticationToken =
          new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
      Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
      SecurityContextHolder.getContext().setAuthentication(authentication);
      String jwt = tokenAuthenticationService.createToken(authentication);
      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.add(SecurityConstants.AUTHORIZATION_HEADER, jwt);
      return new ResponseEntity<>("{}", httpHeaders, HttpStatus.OK);
    } catch (DisabledException ex) {
      throw new CustomDisableException();
    } catch (BadCredentialsException ex) {
      throw new CustomBadCredentialsException();
    } catch (InternalAuthenticationServiceException ex) {
      LOGGER.debug(ex.getClass());
      LOGGER.debug(ex.getMessage());
      return new ResponseEntity<>(new ExceptionResource(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // TODO catch all security exceptions if needed f.e locked user
  }

}

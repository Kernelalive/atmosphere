package eubrazil.atmosphere.security.authentication;

import eubrazil.atmosphere.model.User;
import eubrazil.atmosphere.security.exception.UserNotFoundException;
import eubrazil.atmosphere.service.UserService;
import eubrazil.atmosphere.service.exception.UserUsernameDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationService {

  @Autowired
  private UserService<User> userService;

  /**
   * Retrieve the current logged-in user.
   *
   * @return An instance of CurrentUser object
   */
  public Authentication getCurrentUser() {
    return SecurityContextHolder.getContext().getAuthentication();
  }

  /**
   * Retrieve the current logged-in user name.
   *
   * @return A string of CurrentUser name(probably username)
   */
  public String getCurrentUserName() {
    return SecurityContextHolder.getContext().getAuthentication().getName();
  }

  /**
   * Check if a user has a JWT AccessToken.
   *
   * @return True if user has a JWT in headers, otherwise returns false
   */
  public boolean hasAccessToken() {
    return SecurityContextHolder.getContext().getAuthentication() instanceof UsernamePasswordAuthenticationToken;
  }

  /**
   * Returns currently authenticated user from database.
   *
   * @return An instance of User object
   * @throws UserNotFoundException User does not exist in the database
   */
  public User getCurrentUserFromDb() throws UserNotFoundException {
    // Get current user
    try {
      return userService.findByUsername(getCurrentUserName());
    } catch (UserUsernameDoesNotExistException ex) {
      // if user not found that means that user does not exist anymore
      throw new UserNotFoundException();
    }
  }

}

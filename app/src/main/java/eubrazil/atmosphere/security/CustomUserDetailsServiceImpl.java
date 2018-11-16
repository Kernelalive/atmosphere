package eubrazil.atmosphere.security;

import eubrazil.atmosphere.model.User;
import eubrazil.atmosphere.service.UserService;
import eubrazil.atmosphere.service.exception.UserUsernameDoesNotExistException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * The CustomUserDetailsServiceImpl class implements custom functionality for user authorization.
 *
 * @author Chris Paraskeva
 */
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

  private static final Logger LOGGER = LogManager.getLogger(CustomUserDetailsServiceImpl.class);
  private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();
  private final UserService<User> userService;

  @Autowired
  public CustomUserDetailsServiceImpl(UserService<User> userService) {
    this.userService = userService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    LOGGER.trace("Creating spring authenticated user by username: " + username);
    // Attempt to fetch user from database
    User user;
    try {
      user = userService.findByUsername(username);
    } catch (UserUsernameDoesNotExistException ex) {
      throw new UsernameNotFoundException(String.format("User %s has not been found to the database", username));
    }
    // Set the roles of current user
    Set<GrantedAuthority> roles = user.getRolesList().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    // Create a new authenticated user
    org.springframework.security.core.userdetails.User authenticatedUser = new org.springframework.security.core.userdetails.User(user.getUsername(),
        user.getPassword(), user.isEnabled(), true, true, true, roles);
    // Check Details of current user
    //detailsChecker.check(authenticatedUser);
    return authenticatedUser;
  }

}

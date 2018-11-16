package eubrazil.atmosphere.config;

import eubrazil.atmosphere.model.Role;
import eubrazil.atmosphere.model.User;
import eubrazil.atmosphere.service.UserService;
import eubrazil.atmosphere.service.exception.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Created by kontoe on 5/3/2018.
 */
@Profile("init-data")
@Component
public class DataLoader implements ApplicationRunner {

  private static final Logger LOGGER = LogManager.getLogger(DataLoader.class);

  @Autowired
  private UserService<User> userService;

  public void run(ApplicationArguments args) {
    try {
      userService.register(new User("admin", "adminadmin", "nkontoe@ubitech.eu"));
      userService.updateRole("admin", Role.OWNER);
      userService.enableUser("admin");
    } catch (UserUsernameAlreadyExistsException | UserInvalidEmailException
        | UserEmailAlreadyExistsException | UserUsernameTooShortException | UserInvalidUsernameException
        | UserUsernameDoesNotExistException | UserWeakPasswordException ex) {
      LOGGER.warn(ex.getMessage());
    }
  }

}
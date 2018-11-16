package eubrazil.atmosphere.service.impl;

import eubrazil.atmosphere.model.Role;
import eubrazil.atmosphere.model.User;
import eubrazil.atmosphere.repository.UserRepository;
import eubrazil.atmosphere.service.UserService;
import eubrazil.atmosphere.service.exception.*;
import eubrazil.atmosphere.util.Patterns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class UserServiceImpl implements UserService<User> {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public User register(User user) throws UserUsernameAlreadyExistsException, UserUsernameTooShortException, UserWeakPasswordException, UserInvalidEmailException, UserEmailAlreadyExistsException, UserUsernameDoesNotExistException, UserInvalidUsernameException {
    // Check if username is too short
    if (null == user.getUsername() || user.getUsername().length() < MIN_CHARS_FOR_USERNAME) {
      throw new UserUsernameTooShortException();
    }
    if (!Patterns.USERNAME.matcher(user.getUsername()).find()) {
      throw new UserInvalidUsernameException();
    }
    // Check if username already exists
    if (userRepository.findByUsername(user.getUsername()).isPresent()) {
      throw new UserUsernameAlreadyExistsException(user.getUsername());
    }
    // Check if password is weak
    if (null == user.getPassword() || user.getPassword().length() < MIN_CHARS_FOR_PASSWORD) {
      throw new UserWeakPasswordException(MIN_CHARS_FOR_PASSWORD);
    }
    // Check if email is valid
    if (null == user.getEmail() || !Patterns.EMAIL_ADDRESS.matcher(user.getEmail()).find()) {
      throw new UserInvalidEmailException();
    }
    // Check if email already exists
    if (userRepository.findByEmail(user.getEmail()).isPresent()) {
      throw new UserEmailAlreadyExistsException(user.getEmail());
    }
    User newUser = new User();
    newUser.setUsername(user.getUsername());
    newUser.setFullName(user.getFullName());
    newUser.setEmail(user.getEmail());
    newUser.setRole(Role.VIEWER);
    // New user is not enabled
    newUser.setEnabled(false);
    // Encrypt password
    newUser.setPassword(passwordEncoder.encode(user.getPassword()));
    // Store user to database
    userRepository.save(newUser);
    return findByUsername(newUser.getUsername());
  }

  @Override
  public User findByUsername(String username) throws UserUsernameDoesNotExistException {
    return userRepository.findByUsername(username)
            .orElseThrow(() -> new UserUsernameDoesNotExistException(username));
  }

  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }

  @Override
  public Page<User> findAll(Pageable page) {
    return userRepository.findAll(page);
  }

  @Override
  public void updateProfile(String username, User user) throws UserUsernameDoesNotExistException {
    User current = findByUsername(username);
    // Full name
    current.setFullName(user.getFullName());
    // Store user to database
    userRepository.save(current);
  }

  @Override
  public void updatePassword(String username, String oldPassword, String newPassword, String matchingPassword) throws UserUsernameDoesNotExistException, UserOldPasswordDoesNotMatchException, UserMatchingPasswordDoesNotMatchException, UserWeakPasswordException {
    User user = findByUsername(username);
    // Check if password is weak
    if (null == newPassword || newPassword.length() < MIN_CHARS_FOR_PASSWORD) {
      throw new UserWeakPasswordException(MIN_CHARS_FOR_PASSWORD);
    }
    // Check old password
    if (!BCrypt.checkpw(oldPassword, user.getPassword())) {
      throw new UserOldPasswordDoesNotMatchException();
    }
    // Check matching password
    if (!newPassword.equals(matchingPassword)) {
      throw new UserMatchingPasswordDoesNotMatchException();
    }
    // Encrypt password
    user.setPassword(passwordEncoder.encode(newPassword));
    userRepository.save(user);
  }

  @Override
  public void updateRole(String username, Role role) throws UserUsernameDoesNotExistException {
    User user = findByUsername(username);
    user.setRole(role);
    // Store user to database
    userRepository.save(user);
  }

  @Override
  public void enableUser(String username) throws UserUsernameDoesNotExistException {
    User user = findByUsername(username);
    user.setEnabled(true);
    // Store user to database
    userRepository.save(user);
  }

  @Override
  public void disableUser(String username) throws UserUsernameDoesNotExistException {
    User user = findByUsername(username);
    user.setEnabled(false);
    // Store user to database
    userRepository.save(user);
  }

}

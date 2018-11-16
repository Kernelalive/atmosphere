package eubrazil.atmosphere.service;

import eubrazil.atmosphere.model.Role;
import eubrazil.atmosphere.service.exception.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService<U> {

  int MIN_CHARS_FOR_USERNAME = 3;
  int MIN_CHARS_FOR_PASSWORD = 8;

  U register(U user) throws UserUsernameAlreadyExistsException, UserUsernameTooShortException, UserWeakPasswordException, UserInvalidEmailException, UserEmailAlreadyExistsException, UserUsernameDoesNotExistException, UserInvalidUsernameException;

  U findByUsername(String username) throws UserUsernameDoesNotExistException;

  List<U> findAll();

  Page<U> findAll(Pageable page);

  void updateProfile(String username, U user) throws UserUsernameDoesNotExistException;

  void updatePassword(String username, String oldPassword, String newPassword, String matchingPassword) throws UserUsernameDoesNotExistException, UserOldPasswordDoesNotMatchException, UserMatchingPasswordDoesNotMatchException, UserWeakPasswordException;

  void updateRole(String username, Role role) throws UserUsernameDoesNotExistException;

  void enableUser(String username) throws UserUsernameDoesNotExistException;

  void disableUser(String username) throws UserUsernameDoesNotExistException;

}

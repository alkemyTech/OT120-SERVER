package com.alkemy.ong.service;

import com.alkemy.ong.common.JwtUtil;
import com.alkemy.ong.exception.FirstNameInvalidException;
import com.alkemy.ong.exception.LastNameInvalidException;
import com.alkemy.ong.exception.UsernameAlreadyExistsException;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.repository.IUserRepository;
import com.alkemy.ong.service.abstraction.IDeleteUserService;
import com.alkemy.ong.service.abstraction.IGetUserService;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService, IDeleteUserService, IGetUserService {

  private static final String USER_NOT_FOUND_MESSAGE = "User not found.";

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private IUserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return getUser(username);
  }

  @Override
  public User getBy(String authorizationHeader) {
    return getUser(jwtUtil.extractUsername(authorizationHeader));
  }

  @Override
  public void delete(Long id) throws EntityNotFoundException {
    User user = getUser(id);
    user.setSoftDeleted(true);
    userRepository.save(user);
  }

  private User getUser(Long id) {
    Optional<User> userOptional = userRepository.findById(id);
    if (userOptional.isEmpty() || userOptional.get().isSoftDeleted()) {
      throw new EntityNotFoundException(USER_NOT_FOUND_MESSAGE);
    }
    return userOptional.get();
  }

  private User getUser(String username) {
    User user = userRepository.findByEmail(username);
    if (user == null) {
      throw new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE);
    }
    return user;
  }

  public User postUser(String firstName, String lastName, String email, String password) {

    if (emailExists(email)) {
      throw new UsernameAlreadyExistsException("Ya existe una cuenta con ese usuario.");
    }

    if (!containsOnlyLetters(firstName)) {
      throw new FirstNameInvalidException("El nombre sólo puede contener letras.");
    }

    if (!containsOnlyLetters(lastName)) {
      throw new LastNameInvalidException("El apellido sólo puede contener letras.");
    }

    User user = new User();
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setEmail(email);
    user.setPassword(passwordEncoder.encode(password));

    return userRepository.save(user);
  }

  private boolean emailExists(final String email) {
    return userRepository.findByEmail(email) != null;
  }

  public static boolean containsOnlyLetters(String cadena) {
    for (int x = 0; x < cadena.length(); x++) {
      char c = cadena.charAt(x);
      // Si no está entre a y z, ni entre A y Z, ni es un espacio
      if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
        return false;
      }
    }
    return true;
  }
}

package com.alkemy.ong.service;

import com.alkemy.ong.common.JwtUtil;
import com.alkemy.ong.mapper.UserMapper;
import com.alkemy.ong.model.entity.User;
<<<<<<< HEAD
import com.alkemy.ong.model.request.RegistrationRequest;
import com.alkemy.ong.model.response.UserDtoResponse;
=======
import com.alkemy.ong.dto.UserDto;
>>>>>>> 927862f5d456e34a70b0e31f5aab0e9cfb399de3
import com.alkemy.ong.repository.IUserRepository;
import com.alkemy.ong.service.abstraction.IGetUserService;

import java.util.Optional;
import javax.persistence.EntityNotFoundException;

<<<<<<< HEAD
import javassist.NotFoundException;
=======
import com.alkemy.ong.service.abstraction.IUserService;
>>>>>>> 927862f5d456e34a70b0e31f5aab0e9cfb399de3
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService, IGetUserService, IUserService {

    private static final String USER_NOT_FOUND_MESSAGE = "User not found.";
    public static final String MSG_EMAIL_NOT_FOUND = "Este mail no es un usuario registrado. ";
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

<<<<<<< HEAD
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUser(username);
    }

    @Override
    public User getBy(String authorizationHeader) {
        return getUser(jwtUtil.extractUsername(authorizationHeader));
    }

    @Override
    public UserDtoResponse getMe(String jwt) throws NotFoundException {
        String emailUser = jwtUtil.extractUsername(jwt.substring(7));

        User userEntity = userRepository.findByEmail(emailUser);

        if (userEntity == null) {
            throw new NotFoundException(MSG_EMAIL_NOT_FOUND);
        }
        return entityToDto(userEntity);
    }

    public UserDtoResponse entityToDto(User user) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(user, UserDtoResponse.class);
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

    public User postUser(RegistrationRequest req) {

        User user = new User();

        if (emailExists(req.getEmail())) {
            throw new FieldInvalidException("Ya existe una cuenta con ese usuario.");
        }

        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));

        return userRepository.save(user);
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }
=======
  @Autowired
  private UserMapper userMapper;

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private UserDto userRequestDto;

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

  private boolean emailExists(String email) {
    return userRepository.findByEmail(email) != null;
  }
>>>>>>> 927862f5d456e34a70b0e31f5aab0e9cfb399de3

    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE);
        }
        return user;
    }

  @Override
  public UserDto save(UserDto userRequestDto) {
    User user = userMapper.userDtoToEntity(userRequestDto);
    User userSaved = userRepository.save(user);
    UserDto result = userMapper.entityToUserDto(userSaved);
    return result;
  }
}

package com.alkemy.ong.service;

import com.alkemy.ong.common.JwtUtil;
import com.alkemy.ong.dto.UsersResponseDto;
import com.alkemy.ong.mapper.UserMapper;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.repository.IUserRepository;
import com.alkemy.ong.service.abstraction.IGetAllUsers;
import com.alkemy.ong.service.abstraction.IGetUserService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import com.alkemy.ong.service.abstraction.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserDetailsService, IGetUserService, IUserService ,IGetAllUsers {

  private static final String USER_NOT_FOUND_MESSAGE = "User not found.";

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private IUserRepository userRepository;

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

  @Override
  public List<UsersResponseDto> getAllUsers() {
    return userRepository.findAll().stream().map(userMapper::usersDtoResponse).collect(Collectors.toList());

  }
}

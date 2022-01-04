package com.alkemy.ong.service;

import com.alkemy.ong.common.JwtUtil;

import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.dto.UsersResponseDto;
import com.alkemy.ong.mapper.UserMapper;
import com.alkemy.ong.dto.UserDtoRequest;
import com.alkemy.ong.dto.UserDtoResponse;
import com.alkemy.ong.exception.ParamNotFound;
import com.alkemy.ong.repository.IUserRepository;
import com.alkemy.ong.service.abstraction.IEmailService;
import com.alkemy.ong.service.abstraction.IGetAllUsers;
import com.alkemy.ong.service.abstraction.IGetUserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import com.alkemy.ong.service.abstraction.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserDetailsService, IGetUserService, IUserService, IGetAllUsers {

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
    private UserDtoRequest userRequestDto;

    @Autowired
    IEmailService emailService;


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

    @Override
    public UserDtoResponse update(Long id, UserDtoRequest userDTORequest) {
        Optional<User> userEntity = this.userRepository.findById(id);
        if (!userEntity.isPresent()) {
            throw new ParamNotFound("User id not valid!");
        }
        this.userMapper.UserRefreshValues(userEntity.get(), userDTORequest);
        User entitySaved = this.userRepository.save(userEntity.get());
        UserDtoResponse result = this.userMapper.userEntity2Dto(entitySaved, true);
        return result;
    }

    @Override
    public User getInfoUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            String username = ((User)principal).getUsername();
        } else {
            String username = principal.toString();
        }

        return userRepository.findByEmail(principal.toString());

    }

    @Override
    @Transactional
    public User findByEmail(String email) {

        return userRepository.findByEmail(email);

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
    public UserDtoResponse save(UserDtoRequest userRequestDto) {
        User user = userMapper.userDtoToEntity(userRequestDto);
        User userSaved = userRepository.save(user);
        UserDtoResponse result = userMapper.userEntity2Dto(userSaved, false);
        if (result != null) {
            emailService.sendWelcomeEmail(userRequestDto);
        }
        return result;
    }

    @Override
    public List<UsersResponseDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::usersDtoResponse).collect(Collectors.toList());
    }
}

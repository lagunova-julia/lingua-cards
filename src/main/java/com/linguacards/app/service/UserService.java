package com.linguacards.app.service;

import com.linguacards.app.dto.user.UserCreateDTO;
import com.linguacards.app.dto.user.UserDTO;
import com.linguacards.app.dto.user.UserUpdateDTO;
import com.linguacards.app.exception.ResourceNotFoundException;
import com.linguacards.app.mapper.UserMapper;
import com.linguacards.app.model.User;
import com.linguacards.app.repository.UserRepository;
import com.linguacards.app.service.models.UserServiceModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public List<UserServiceModel> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toServiceModel)
                .toList();
    }

    public UserServiceModel create(UserCreateDTO userData) {
        UserServiceModel user = userMapper.toServiceModel(userData);
        User entity = userMapper.toEntity(user);
        entity.setPassword(passwordEncoder.encode(userData.getPassword()));
        entity.setRoles(Set.of("ROLE_USER"));
        userRepository.save(entity);
        return userMapper.toServiceModel(entity);
    }

    public UserServiceModel show(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found: " + id));

        return userMapper.toServiceModel(user);
    }

    public UserServiceModel update(UserUpdateDTO userData, Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User " + id + " not found"));

        userMapper.update(userData, user);
        userRepository.save(user);
        return userMapper.toServiceModel(user);
    }

    public void delete(Long id) throws ResponseStatusException {
        userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User " + id + " not found"));

        userRepository.deleteById(id);
    }

    public Long getIdByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email))
                .getId();
    }
}

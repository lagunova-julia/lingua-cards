package com.linguacards.app.controller;

import com.linguacards.app.dto.user.UserCreateDTO;
import com.linguacards.app.dto.user.UserDTO;
import com.linguacards.app.dto.user.UserUpdateDTO;
import com.linguacards.app.mapper.UserMapper;
import com.linguacards.app.service.UserService;
import com.linguacards.app.service.models.UserServiceModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping(path = "")
    public ResponseEntity<List<UserDTO>> index() {
        List<UserDTO> users = userService.getAll()
                .stream()
                .map(userMapper::toDTO)
                .toList();

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(users.size()));

        return new ResponseEntity<>(users, headers, HttpStatus.OK);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@Valid @RequestBody UserCreateDTO userData) {
        UserServiceModel usm = userService.create(userData);
        return userMapper.toDTO(usm);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO show(@PathVariable Long id) {
        UserServiceModel usm = userService.show(id);
        return userMapper.toDTO(usm);
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == @userService.getIdByEmail(authentication.name)")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO update(@RequestBody @Valid UserUpdateDTO userData, @PathVariable Long id) {
        UserServiceModel usm = userService.update(userData, id);
        return userMapper.toDTO(usm);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == @userService.getIdByEmail(authentication.name)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws ResponseStatusException {
        userService.delete(id);
    }
}

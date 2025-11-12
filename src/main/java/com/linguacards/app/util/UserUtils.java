package com.linguacards.app.util;

import com.linguacards.app.model.User;
import com.linguacards.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class UserUtils {
    private final UserRepository userRepository;

    public User getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        var email = authentication.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"));
    }

    //FILL IN THE FIELD EMAIL!! or if it's for the test, getTestUser(String email) -> findByEmail(email)
    public User getTestUser() {
        return  userRepository.findByEmail("write-admin-email-here")
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}

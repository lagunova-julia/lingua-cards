package com.linguacards.app.component;

import com.linguacards.app.model.User;
import com.linguacards.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public final class DataInitializer implements ApplicationRunner {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initAdmin();
    }

    private void initAdmin() {
        if (userRepository.findByEmail("admin@email.com").isEmpty()) {
            User user = new User();
            user.setEmail("admin@email.com");
            user.setPassword(passwordEncoder.encode("the-password"));
            user.setRoles(Set.of("ROLE_ADMIN"));

            userRepository.save(user);
        }
    }
}

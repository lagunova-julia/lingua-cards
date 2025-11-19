package com.linguacards.app.component;

import com.linguacards.app.model.Language;
import com.linguacards.app.model.Translation;
import com.linguacards.app.model.User;
import com.linguacards.app.model.Word;
import com.linguacards.app.repository.LanguageRepository;
import com.linguacards.app.repository.TranslationRepository;
import com.linguacards.app.repository.UserRepository;
import com.linguacards.app.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public final class DataInitializer implements ApplicationRunner {
    private final LanguageRepository languageRepository;
    private final WordRepository wordRepository;
    private final TranslationRepository translationRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initAdmin();
        unitTranslations();
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

    private void unitTranslations() {
        log.info("Initializing test data...");

        Language english = languageRepository.save(Language.builder()
                .code("en").name("English").build());
        Language russian = languageRepository.save(Language.builder()
                .code("ru").name("Russian").build());

        Word appleEn = wordRepository.save(Word.builder()
                .text("apple").language(english).build());
        Word appleRu = wordRepository.save(Word.builder()
                .text("яблоко").language(russian).build());

        Word catEn = wordRepository.save(Word.builder()
                .text("cat").language(english).build());
        Word catRu = wordRepository.save(Word.builder()
                .text("кошка").language(russian).build());

        translationRepository.save(Translation.builder()
                .wordFrom(appleEn).wordTo(appleRu).build());
        translationRepository.save(Translation.builder()
                .wordFrom(catEn).wordTo(catRu).build());

        log.info("Test data initialized successfully");
    }
}

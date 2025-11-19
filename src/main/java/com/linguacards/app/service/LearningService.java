package com.linguacards.app.service;

import com.linguacards.app.dto.CardDTO;
import com.linguacards.app.dto.UserProgressDTO;
import com.linguacards.app.exception.NoCardsAvailableException;
import com.linguacards.app.exception.ResourceNotFoundException;
import com.linguacards.app.model.ProgressStatus;
import com.linguacards.app.model.Translation;
import com.linguacards.app.model.User;
import com.linguacards.app.model.UserProgress;
import com.linguacards.app.repository.TranslationRepository;
import com.linguacards.app.repository.UserProgressRepository;
import com.linguacards.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LearningService {
    private final TranslationRepository translationRepository;
    private final UserProgressRepository userProgressRepository;
    private final UserRepository userRepository;
    public CardDTO getNextCard(Long userId, String fromLang, String toLang) {
        Translation randomCard = translationRepository
                .findRandomUnlearnedByUserAndLanguages(userId, fromLang, toLang)
                .orElseThrow(() -> new NoCardsAvailableException("No cards to learn"));

        return buildCardDTO(randomCard);
    }

    private CardDTO buildCardDTO(Translation card) {
        return CardDTO.builder()
                .cardId(card.getId())
                .word(card.getWordFrom().getText())
                .translation(card.getWordTo().getText())
                .fromLang(card.getWordFrom().getLanguage().getCode())
                .toLang(card.getWordTo().getLanguage().getCode())
                .build();
    }

    public UserProgressDTO markAsLearned(Long userId, Long cardId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("There is no user with ID " + userId));

        Translation translation = translationRepository.findById(cardId)
                .orElseThrow(() -> new ResourceNotFoundException("There is no card with ID " + cardId));

        UserProgress progress = UserProgress.builder()
                .user(user)
                .translation(translation)
                .status(ProgressStatus.LEARNED)
                .successCount(1)
                .build();

        userProgressRepository.save(progress);
        return buildUserProgressDTO(progress);
    }

    private UserProgressDTO buildUserProgressDTO(UserProgress userProgress) {
        return UserProgressDTO.builder()
                .userProgressId(userProgress.getId())
                .userId(userProgress.getUser().getId())
                .translationId(userProgress.getTranslation().getId())
                .status(userProgress.getStatus())
                .build();
    }

}

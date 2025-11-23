package com.linguacards.app.service;

import com.linguacards.app.dto.CardDTO;
import com.linguacards.app.dto.UserProgressDTO;
import com.linguacards.app.exception.NoCardsAvailableException;
import com.linguacards.app.exception.ResourceNotFoundException;
import com.linguacards.app.mapper.CardMapper;
import com.linguacards.app.mapper.UserProgressMapper;
import com.linguacards.app.model.ProgressStatus;
import com.linguacards.app.model.Translation;
import com.linguacards.app.model.User;
import com.linguacards.app.model.UserProgress;
import com.linguacards.app.repository.TranslationRepository;
import com.linguacards.app.repository.UserProgressRepository;
import com.linguacards.app.repository.UserRepository;
import com.linguacards.app.service.models.CardServiceModel;
import com.linguacards.app.service.models.UserProgressServiceModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class LearningService {
    private final TranslationRepository translationRepository;
    private final UserProgressRepository userProgressRepository;
    private final UserRepository userRepository;
    private final CardMapper cardMapper;
    private final UserProgressMapper userProgressMapper;
    public CardServiceModel getNextCard(Long userId, String fromLang, String toLang) {
        Translation entity = translationRepository
                .findRandomUnlearnedByUserAndLanguages(userId, fromLang, toLang)
                .orElseThrow(() -> new NoCardsAvailableException("No cards to learn"));

        return cardMapper.toServiceModel(entity);
    }

    public UserProgressServiceModel markAsLearned(Long userId, Long cardId) {
        validateUserAndCard(userId, cardId);

        UserProgressServiceModel progressModel = UserProgressServiceModel.builder()
                .userId(userId)
                .translationId(cardId)
                .status(ProgressStatus.LEARNED)
                .successCount(1)
                .lastReviewed(LocalDateTime.now())
                .build();

        UserProgress userProgress = userProgressMapper.toEntity(progressModel);

        User user = userRepository.getReferenceById(userId);
        Translation translation = translationRepository.getReferenceById(cardId);
        userProgress.setUser(user);
        userProgress.setTranslation(translation);
        UserProgress savedEntity = userProgressRepository.save(userProgress);

        return userProgressMapper.toServiceModel(savedEntity);
    }

    private void validateUserAndCard(Long userId, Long cardId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("There is no user with ID " + userId));

        translationRepository.findById(cardId)
                .orElseThrow(() -> new ResourceNotFoundException("There is no card with ID " + cardId));
    }
}

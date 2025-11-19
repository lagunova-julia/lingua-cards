package com.linguacards.app.controller;

import com.linguacards.app.dto.CardDTO;
import com.linguacards.app.dto.UserProgressDTO;
import com.linguacards.app.service.LearningService;
import com.linguacards.app.util.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/learn")
public class LearningController {
    private final LearningService learningService;
    private final UserUtils userUtils;
    @GetMapping(path = "/next")
    @ResponseStatus(HttpStatus.OK)
    public CardDTO getNextCard(@RequestParam String fromLang,
                               @RequestParam String toLang) {
        Long currentUserId = userUtils.getCurrentUser().getId();

        return learningService.getNextCard(currentUserId, fromLang, toLang);
    } //URL: /api/learn/next?fromLang=en&toLang=ru

    @PostMapping(path = "/mark-learned/{cardId}")
    @ResponseStatus(HttpStatus.OK)
    public UserProgressDTO markAsLearned(@PathVariable Long cardId) {
        Long currentUserId = userUtils.getCurrentUser().getId();
        return learningService.markAsLearned(currentUserId, cardId);
    }
}

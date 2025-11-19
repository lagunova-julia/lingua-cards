package com.linguacards.app.controller;

import com.linguacards.app.model.Language;
import com.linguacards.app.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/languages")
public class LanguageController {
    private final LanguageRepository languageRepository;
    @GetMapping(path = "")
    public List<Language> index() {
        return languageRepository.findAll();
    }
}

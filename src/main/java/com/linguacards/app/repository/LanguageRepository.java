package com.linguacards.app.repository;

import com.linguacards.app.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, Long> {
//    Optional<Language> findByCode(String code);
}

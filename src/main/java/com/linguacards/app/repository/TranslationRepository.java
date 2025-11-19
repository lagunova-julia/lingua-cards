package com.linguacards.app.repository;

import com.linguacards.app.model.Translation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TranslationRepository extends JpaRepository<Translation, Long> {

    @Query(value = "SELECT t.* FROM translations t " +
            "INNER JOIN words wf ON t.word_id_from = wf.id " +
            "INNER JOIN languages lf ON wf.language_id = lf.id " +
            "INNER JOIN words wt ON t.word_id_to = wt.id " +
            "INNER JOIN languages lt ON wt.language_id = lt.id " +
            "WHERE lf.code = :fromLang " +
            "AND lt.code = :toLang " +
            "AND NOT EXISTS (" +
            "   SELECT 1 FROM user_progress up " +
            "   WHERE up.translation_id = t.id AND up.user_id = :userId" +
            ") " +
            "ORDER BY RANDOM() LIMIT 1",
            nativeQuery = true)
    Optional<Translation> findRandomUnlearnedByUserAndLanguages(@Param("userId") Long userId,
                                                            @Param("fromLang") String fromLang,
                                                            @Param("toLang") String toLang);

}

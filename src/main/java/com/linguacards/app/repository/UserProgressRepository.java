package com.linguacards.app.repository;

import com.linguacards.app.model.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProgressRepository extends JpaRepository<UserProgress, Long> {

}

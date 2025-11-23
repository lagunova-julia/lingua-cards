package com.linguacards.app.service.models;

import com.linguacards.app.model.ProgressStatus;
import jakarta.persistence.EntityListeners;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class UserProgressServiceModel {
    private Long id;
    private Long userId;
    private Long translationId;
    private ProgressStatus status;
    private Integer successCount;
    private Integer failCount;
    private LocalDateTime lastReviewed;
}

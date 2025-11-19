package com.linguacards.app.dto;

import com.linguacards.app.model.ProgressStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProgressDTO {
    private Long userProgressId;
    private Long userId;
    private Long translationId;
    private ProgressStatus status;
}

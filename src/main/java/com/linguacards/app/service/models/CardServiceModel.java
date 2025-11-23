package com.linguacards.app.service.models;

import com.linguacards.app.model.ProgressStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardServiceModel {
    private Long cardId;
    private String word;
    private String translation;
    private String fromLang;
    private String toLang;
//    private ProgressStatus status;
}

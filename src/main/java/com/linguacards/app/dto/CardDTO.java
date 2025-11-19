package com.linguacards.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {
    private long cardId;
    private String word;
    private String translation;
    private String fromLang;
    private String toLang;

}

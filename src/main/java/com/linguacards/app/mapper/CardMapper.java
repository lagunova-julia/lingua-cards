package com.linguacards.app.mapper;

import com.linguacards.app.dto.CardDTO;
import com.linguacards.app.model.Translation;
import com.linguacards.app.service.models.CardServiceModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class CardMapper {
    public abstract CardDTO toDTO(CardServiceModel serviceModel);
    public abstract CardServiceModel toServiceModel(CardDTO dto);

    public CardServiceModel toServiceModel(Translation entity) {
        return CardServiceModel.builder()
                .cardId(entity.getId())
                .word(entity.getWordFrom().getText())
                .translation(entity.getWordTo().getText())
                .fromLang(entity.getWordFrom().getLanguage().getCode())
                .toLang(entity.getWordTo().getLanguage().getCode())
                .build();
    }
}

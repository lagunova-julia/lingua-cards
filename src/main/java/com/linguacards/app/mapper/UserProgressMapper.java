package com.linguacards.app.mapper;

import com.linguacards.app.dto.UserProgressDTO;
import com.linguacards.app.model.UserProgress;
import com.linguacards.app.service.models.UserProgressServiceModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class UserProgressMapper {
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "translationId", source = "translation.id")
    public abstract UserProgressServiceModel toServiceModel(UserProgress entity);

    public abstract UserProgressDTO toDTO(UserProgressServiceModel serviceModel);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "translation", ignore = true)
    public abstract UserProgress toEntity(UserProgressServiceModel serviceModel);
}

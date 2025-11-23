package com.linguacards.app.mapper;

import com.linguacards.app.dto.user.UserCreateDTO;
import com.linguacards.app.dto.user.UserDTO;
import com.linguacards.app.dto.user.UserUpdateDTO;
import com.linguacards.app.model.User;
import com.linguacards.app.service.models.UserServiceModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        uses = { JsonNullableMapper.class },
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class UserMapper {
    public abstract UserServiceModel toServiceModel(User entity);
    public abstract User toEntity(UserServiceModel serviceModel);
    public abstract UserServiceModel toServiceModel(UserCreateDTO dto);
    public abstract UserDTO toDTO(UserServiceModel serviceModel);
    public abstract void update(UserUpdateDTO dto, @MappingTarget User model);
}

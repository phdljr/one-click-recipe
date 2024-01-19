package org.springeel.oneclickrecipe.domain.user.mapper.entity;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springeel.oneclickrecipe.domain.user.dto.service.UserSignUpServiceRequestDto;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springeel.oneclickrecipe.domain.user.entity.UserRole;

@Mapper(
    componentModel = SPRING,
    uses = PasswordTranslator.class
)
public interface UserEntityMapper {

    @Mapping(source = "serviceRequestDto.password", target = "password", qualifiedBy = EncodePassword.class)
    @Mapping(source = "userRole", target = "role")
    User toUser(UserSignUpServiceRequestDto serviceRequestDto, UserRole userRole);
}

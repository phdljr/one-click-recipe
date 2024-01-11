package org.springeel.oneclickrecipe.domain.user.mapper.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.springeel.oneclickrecipe.domain.user.dto.service.UserSignUpServiceRequestDto;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springeel.oneclickrecipe.domain.user.entity.UserRole;

@Mapper(
    componentModel = ComponentModel.SPRING,
    uses = PasswordTranslator.class
)
public interface UserEntityMapper {

    @Mapping(target = "password", qualifiedBy = EncodePassword.class)
    @Mapping(source = "userRole", target = "role")
    User toUser(UserSignUpServiceRequestDto serviceRequestDto, UserRole userRole);
}

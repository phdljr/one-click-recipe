package org.springeel.oneclickrecipe.domain.user.mapper.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springeel.oneclickrecipe.domain.user.dto.service.request.UserSignUpServiceRequestDto;
import org.springeel.oneclickrecipe.domain.user.dto.service.response.UserLoginResponseDto;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springeel.oneclickrecipe.domain.user.entity.UserRole;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
    componentModel = SPRING,
    uses = PasswordTranslator.class
)
public interface UserEntityMapper {

    @Mapping(source = "serviceRequestDto.password", target = "password", qualifiedBy = EncodePassword.class)
    @Mapping(source = "userRole", target = "role")
    User toUser(UserSignUpServiceRequestDto serviceRequestDto, UserRole userRole);

    UserLoginResponseDto toUserLoginResponseDto(User user);
}

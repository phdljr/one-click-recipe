package org.springeel.oneclickrecipe.domain.user.dto.service.response;

import org.springeel.oneclickrecipe.domain.user.entity.UserRole;

public record UserLoginResponseDto(
    Long id,
    String nickname,
    String email,
    UserRole role
) {

}

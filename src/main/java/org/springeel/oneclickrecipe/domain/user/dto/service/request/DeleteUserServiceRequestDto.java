package org.springeel.oneclickrecipe.domain.user.dto.service.request;

public record DeleteUserServiceRequestDto(
    String password,
    String confirmPassword
) {
}

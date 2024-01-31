package org.springeel.oneclickrecipe.domain.user.dto.service.request;

public record PasswordUpdateServiceRequestDto(
    String newPassword,
    String confirmNewPassword,
    String currentPassword
) {
}

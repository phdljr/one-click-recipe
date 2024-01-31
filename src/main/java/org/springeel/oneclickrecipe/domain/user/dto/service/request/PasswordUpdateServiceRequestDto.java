package org.springeel.oneclickrecipe.domain.user.dto.service.request;

public record PasswordUpdateServiceRequestDto(
    String currentPassword,
    String newPassword,
    String confirmNewPassword
) {

}

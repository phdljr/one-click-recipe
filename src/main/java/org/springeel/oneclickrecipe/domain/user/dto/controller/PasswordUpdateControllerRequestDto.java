package org.springeel.oneclickrecipe.domain.user.dto.controller;

public record PasswordUpdateControllerRequestDto(
    String newPassword,
    String confirmNewPassword,
    String currentPassword
) {
}

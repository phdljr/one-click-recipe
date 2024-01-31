package org.springeel.oneclickrecipe.domain.user.dto.controller;

public record PasswordUpdateControllerRequestDto(
    String currentPassword,
    String newPassword,
    String confirmNewPassword
) {

}

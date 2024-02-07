package org.springeel.oneclickrecipe.domain.user.dto.controller;

import jakarta.validation.constraints.Size;

public record PasswordUpdateControllerRequestDto(
    @Size(min = 8)
    String currentPassword,
    @Size(min = 8)
    String newPassword,
    @Size(min = 8)
    String confirmNewPassword
) {

}

package org.springeel.oneclickrecipe.domain.user.dto.controller;

public record DeleteUserControllerRequestDto(
    String password,
    String confirmPassword
) {
}

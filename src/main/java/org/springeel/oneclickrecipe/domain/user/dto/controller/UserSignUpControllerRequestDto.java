package org.springeel.oneclickrecipe.domain.user.dto.controller;

public record UserSignUpControllerRequestDto(
    String email,
    String nickname,
    String password,
    String confirmPassword
) {

}

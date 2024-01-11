package org.springeel.oneclickrecipe.domain.user.dto.service;

public record UserSignUpServiceRequestDto(
    String email,
    String nickname,
    String password,
    String confirmPassword
) {

}

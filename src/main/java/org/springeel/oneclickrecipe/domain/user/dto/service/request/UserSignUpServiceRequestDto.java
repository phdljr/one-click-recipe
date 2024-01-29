package org.springeel.oneclickrecipe.domain.user.dto.service.request;

public record UserSignUpServiceRequestDto(
    String email,
    String nickname,
    String password,
    String confirmPassword
) {

}

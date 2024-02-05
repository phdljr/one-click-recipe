package org.springeel.oneclickrecipe.domain.user.dto.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserSignUpControllerRequestDto(
    @Email
    String email,

    @Size(min = 4, max = 20)
    String nickname,

    @Size(min = 8)
    String password,

    @Size(min = 8)
    String confirmPassword
) {

}

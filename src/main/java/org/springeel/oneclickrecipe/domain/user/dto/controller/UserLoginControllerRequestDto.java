package org.springeel.oneclickrecipe.domain.user.dto.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserLoginControllerRequestDto(
    @Email
    String email,

    @Size(min = 8)
    String password
) {

}

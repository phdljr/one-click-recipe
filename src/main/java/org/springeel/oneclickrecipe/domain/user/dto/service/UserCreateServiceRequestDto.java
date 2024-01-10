package org.springeel.oneclickrecipe.domain.user.dto.service;

public record UserCreateServiceRequestDto(
    String email,
    String nickname,
    String password,
    String passwordConfirm
) {

}

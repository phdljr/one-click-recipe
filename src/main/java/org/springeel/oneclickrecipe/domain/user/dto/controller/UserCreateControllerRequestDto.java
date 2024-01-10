package org.springeel.oneclickrecipe.domain.user.dto.controller;

public record UserCreateControllerRequestDto(
    String email,
    String nickname,
    String password,
    String passwordConfirm
) {

}

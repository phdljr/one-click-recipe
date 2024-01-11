package org.springeel.oneclickrecipe.domain.user.dto.service;

public record UserLoginServiceRequestDto(
    String email,
    String password
) {

}

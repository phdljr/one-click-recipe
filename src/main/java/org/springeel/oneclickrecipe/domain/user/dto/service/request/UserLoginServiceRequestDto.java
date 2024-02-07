package org.springeel.oneclickrecipe.domain.user.dto.service.request;

public record UserLoginServiceRequestDto(
    String email,
    String password
) {

}

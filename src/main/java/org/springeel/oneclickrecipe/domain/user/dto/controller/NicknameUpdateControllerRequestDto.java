package org.springeel.oneclickrecipe.domain.user.dto.controller;

import jakarta.validation.constraints.Size;

public record NicknameUpdateControllerRequestDto(
    @Size(min = 4, max = 20)
    String nickname
) {
}

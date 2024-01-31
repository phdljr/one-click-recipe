package org.springeel.oneclickrecipe.domain.user.dto.controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record NicknameUpdateControllerRequestDto(
    @Min(value = 4)
    @Max(value = 20)
    String nickname
) {
}

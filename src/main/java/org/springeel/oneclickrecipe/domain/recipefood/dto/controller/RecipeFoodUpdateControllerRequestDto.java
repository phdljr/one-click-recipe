package org.springeel.oneclickrecipe.domain.recipefood.dto.controller;

public record RecipeFoodUpdateControllerRequestDto(
    String foodName,
    Short amount
) {

}

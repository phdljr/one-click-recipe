package org.springeel.oneclickrecipe.domain.recipefood.dto.controller;

public record RecipeFoodCreateControllerRequestDto(
    String foodName,
    Short amount
) {

}

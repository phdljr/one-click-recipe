package org.springeel.oneclickrecipe.domain.recipefood.dto.service.request;

public record RecipeFoodCreateServiceRequestDto(
    String foodName,
    Short amount
) {

}

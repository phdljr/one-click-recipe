package org.springeel.oneclickrecipe.domain.recipelike.mapper.dto;

import org.springeel.oneclickrecipe.domain.recipelike.dto.controller.RecipeLikeCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.recipelike.dto.service.RecipeLikeCreateServiceRequestDto;

public interface RecipeLikeDtoMapper {
    RecipeLikeCreateServiceRequestDto toRecipeLikeCreateServiceRequestDto(
        RecipeLikeCreateControllerRequestDto createControllerRequestDto);
}

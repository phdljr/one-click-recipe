package org.springeel.oneclickrecipe.domain.recipelike.mapper.dto;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springeel.oneclickrecipe.domain.recipelike.dto.controller.RecipeLikeCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.recipelike.dto.service.RecipeLikeCreateServiceRequestDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)

public interface RecipeLikeDtoMapper {
    RecipeLikeCreateServiceRequestDto toRecipeLikeCreateServiceRequestDto(
        RecipeLikeCreateControllerRequestDto createControllerRequestDto);
}

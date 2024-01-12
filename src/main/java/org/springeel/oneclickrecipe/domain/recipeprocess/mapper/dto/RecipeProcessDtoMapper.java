package org.springeel.oneclickrecipe.domain.recipeprocess.mapper.dto;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.controller.RecipeProcessCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.controller.RecipeProcessUpdateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.service.RecipeProcessCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.service.RecipeProcessUpdateServiceRequestDto;

@Mapper(componentModel = ComponentModel.SPRING)
public interface RecipeProcessDtoMapper {

    RecipeProcessCreateServiceRequestDto toRecipeProcessCreateServiceRequestDto(
        RecipeProcessCreateControllerRequestDto controllerRequestDto);

    RecipeProcessUpdateServiceRequestDto toRecipeProcessUpdateServiceRequestDto(
        RecipeProcessUpdateControllerRequestDto controllerRequestDto);
}

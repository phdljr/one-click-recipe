package org.springeel.oneclickrecipe.domain.recipe.mapper.dto;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.springeel.oneclickrecipe.domain.recipe.dto.controller.RecipeCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.controller.RecipeUpdateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.RecipeCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.RecipeUpdateServiceRequestDto;

@Mapper(componentModel = ComponentModel.SPRING)
public interface RecipeDtoMapper {

    RecipeCreateServiceRequestDto toRecipeServiceRequestDto(
        RecipeCreateControllerRequestDto controllerRequestDto);

    RecipeUpdateServiceRequestDto toRecipeUpdateServiceRequestDto(
        RecipeUpdateControllerRequestDto controllerRequestDto);
}

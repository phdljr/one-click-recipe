package org.springeel.oneclickrecipe.domain.recipe.mapper.dto;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import org.mapstruct.Mapper;
import org.springeel.oneclickrecipe.domain.recipe.dto.controller.RecipeCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.controller.RecipeUpdateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.request.RecipeCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.request.RecipeUpdateServiceRequestDto;

@Mapper(componentModel = SPRING)
public interface RecipeDtoMapper {

    RecipeCreateServiceRequestDto toRecipeCreateServiceRequestDto(
        RecipeCreateControllerRequestDto controllerRequestDto);

    RecipeUpdateServiceRequestDto toRecipeUpdateServiceRequestDto(
        RecipeUpdateControllerRequestDto controllerRequestDto);
}

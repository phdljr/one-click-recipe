package org.springeel.oneclickrecipe.domain.recipeprocess.mapper.dto;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import java.util.List;
import org.mapstruct.Mapper;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.controller.RecipeProcessCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.controller.RecipeProcessUpdateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.service.request.RecipeProcessCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.service.request.RecipeProcessUpdateServiceRequestDto;

@Mapper(componentModel = SPRING)
public interface RecipeProcessDtoMapper {

    List<RecipeProcessCreateServiceRequestDto> toRecipeProcessCreateServiceRequestDtos(
        List<RecipeProcessCreateControllerRequestDto> controllerRequestDto);

    RecipeProcessCreateServiceRequestDto toRecipeProcessCreateServiceRequestDto(
        RecipeProcessCreateControllerRequestDto controllerRequestDto);

    RecipeProcessUpdateServiceRequestDto toRecipeProcessUpdateServiceRequestDto(
        RecipeProcessUpdateControllerRequestDto controllerRequestDto);
}

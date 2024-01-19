package org.springeel.oneclickrecipe.domain.recipe.mapper.entity;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import java.util.List;
import org.mapstruct.Mapper;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.request.RecipeCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.response.RecipeAllReadResponseDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.response.RecipeReadResponseDto;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.user.entity.User;

@Mapper(componentModel = SPRING)
public interface RecipeEntityMapper {

    Recipe toRecipe(RecipeCreateServiceRequestDto requestDto, User user, String folderName);

    List<RecipeAllReadResponseDto> toRecipeAllReadResponseDto(List<Recipe> recipe);

    RecipeReadResponseDto toRecipeReadResponseDto(Recipe recipe);

}

package org.springeel.oneclickrecipe.domain.recipe.mapper.entity;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.RecipeAllReadResponseDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.RecipeCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.RecipeReadResponseDto;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.recipefood.dto.service.RecipeFoodReadResponseDto;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.service.RecipeProcessReadResponseDto;
import org.springeel.oneclickrecipe.domain.user.entity.User;

@Mapper(componentModel = ComponentModel.SPRING)
public interface RecipeEntityMapper {

    Recipe toRecipe(RecipeCreateServiceRequestDto requestDto, User user, String folderName);

    List<RecipeAllReadResponseDto> toRecipeAllRead(List<Recipe> recipe);

    RecipeReadResponseDto toRecipeRead(Recipe recipe, List<RecipeFoodReadResponseDto> recipe_foods,
        List<RecipeProcessReadResponseDto> recipe_processes);

}

package org.springeel.oneclickrecipe.domain.recipe.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springeel.oneclickrecipe.domain.recipe.dto.controller.RecipeCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.RecipeCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springeel.oneclickrecipe.sample.entity.Test;

@Mapper
public interface RecipeMapper {

    RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);

    Recipe toRecipe(RecipeCreateServiceRequestDto recipeRequestDto);

    RecipeCreateServiceRequestDto toRecipeServiceRequestDto(
        RecipeCreateControllerRequestDto controllerRequestDto);
}

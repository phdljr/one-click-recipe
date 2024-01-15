package org.springeel.oneclickrecipe.domain.recipeprocess.mapper.entity;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.service.RecipeProcessCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipeprocess.entity.RecipeProcess;
import org.springeel.oneclickrecipe.domain.user.entity.User;

@Mapper(componentModel = ComponentModel.SPRING)
public interface RecipeProcessEntityMapper {

    RecipeProcess toRecipeProcess(RecipeProcessCreateServiceRequestDto requestDto, String imageUrl,
        Recipe recipe);

}

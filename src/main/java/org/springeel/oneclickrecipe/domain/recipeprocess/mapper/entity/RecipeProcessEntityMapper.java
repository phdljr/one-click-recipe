package org.springeel.oneclickrecipe.domain.recipeprocess.mapper.entity;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.service.RecipeProcessCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.service.RecipeProcessReadResponseDto;
import org.springeel.oneclickrecipe.domain.recipeprocess.entity.RecipeProcess;

@Mapper(componentModel = ComponentModel.SPRING)
public interface RecipeProcessEntityMapper {

    RecipeProcess toRecipeProcess(RecipeProcessCreateServiceRequestDto requestDto, String imageUrl,
        Recipe recipe);

    List<RecipeProcessReadResponseDto> toReadRecipeProcess(
        List<RecipeProcess> recipeProcesses);

}

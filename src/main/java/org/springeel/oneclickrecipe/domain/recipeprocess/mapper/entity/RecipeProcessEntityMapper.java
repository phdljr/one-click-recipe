package org.springeel.oneclickrecipe.domain.recipeprocess.mapper.entity;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.service.request.RecipeProcessCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.service.response.RecipeProcessReadResponseDto;
import org.springeel.oneclickrecipe.domain.recipeprocess.entity.RecipeProcess;

@Mapper(componentModel = SPRING)
public interface RecipeProcessEntityMapper {

    @Mapping(source = "recipe", target = "recipe")
    RecipeProcess toRecipeProcess(RecipeProcessCreateServiceRequestDto requestDto, String imageUrl,
        Recipe recipe);

    List<RecipeProcessReadResponseDto> toRecipeProcessReadResponseDto(
        List<RecipeProcess> recipeProcesses);

}

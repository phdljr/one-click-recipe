package org.springeel.oneclickrecipe.domain.recipe.dto.service;

import java.util.List;
import org.springeel.oneclickrecipe.domain.recipefood.dto.service.RecipeFoodReadResponseDto;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.service.RecipeProcessReadResponseDto;

public record RecipeReadResponseDto(
    String title,
    String intro,
    String videoPath,
    String imageUrl,
    List<RecipeFoodReadResponseDto> recipe_foods,
    List<RecipeProcessReadResponseDto> recipe_processes
) {

}

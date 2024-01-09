package org.springeel.oneclickrecipe.domain.recipe.service.impl;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.RecipeCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.recipe.mapper.RecipeMapper;
import org.springeel.oneclickrecipe.domain.recipe.repository.RecipeRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RecipeServiceImpl {

    private final RecipeRepository recipeRepository;

    public void create(final RecipeCreateServiceRequestDto requestDto) {
        Recipe recipe = RecipeMapper.INSTANCE.toRecipe(requestDto);
        recipeRepository.save(recipe);
    }

}

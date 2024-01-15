package org.springeel.oneclickrecipe.domain.recipelike.service.impl;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.recipe.exception.NotFoundRecipeException;
import org.springeel.oneclickrecipe.domain.recipe.exception.RecipeErrorCode;
import org.springeel.oneclickrecipe.domain.recipe.repository.RecipeRepository;
import org.springeel.oneclickrecipe.domain.recipelike.dto.service.RecipeLikeCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipelike.entity.RecipeLike;
import org.springeel.oneclickrecipe.domain.recipelike.mapper.entity.RecipeLikeEntityMapper;
import org.springeel.oneclickrecipe.domain.recipelike.repository.RecipeLikeRepository;
import org.springeel.oneclickrecipe.domain.recipelike.service.RecipeLikeService;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RecipeLikeServiceImpl implements RecipeLikeService {

    private final RecipeLikeRepository recipeLikeRepository;
    private final RecipeRepository recipeRepository;
    private final RecipeLikeEntityMapper recipeLikeEntityMapper;

    @Override
    public void create(User user, RecipeLikeCreateServiceRequestDto serviceRequestDto, Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
            .orElseThrow(() -> new NotFoundRecipeException(RecipeErrorCode.NOT_FOUND_RECIPE));
        RecipeLike recipeLike = recipeLikeEntityMapper.toRecipeLike(serviceRequestDto, user, recipe);
    }
}

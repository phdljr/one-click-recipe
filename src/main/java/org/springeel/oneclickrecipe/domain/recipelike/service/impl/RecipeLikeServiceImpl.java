package org.springeel.oneclickrecipe.domain.recipelike.service.impl;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.recipe.exception.NotFoundRecipeException;
import org.springeel.oneclickrecipe.domain.recipe.exception.RecipeErrorCode;
import org.springeel.oneclickrecipe.domain.recipe.repository.RecipeRepository;
import org.springeel.oneclickrecipe.domain.recipelike.entity.RecipeLike;
import org.springeel.oneclickrecipe.domain.recipelike.exception.AlreadyExistsRecipeLikeException;
import org.springeel.oneclickrecipe.domain.recipelike.exception.NotFoundRecipeLikeException;
import org.springeel.oneclickrecipe.domain.recipelike.exception.RecipeLikeErrorCode;
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
    public void create(User user, Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
            .orElseThrow(() -> new NotFoundRecipeException(RecipeErrorCode.NOT_FOUND_RECIPE));

        if (recipeLikeRepository.existsByUserIdAndRecipeId(user.getId(), recipe.getId())) {
            throw new AlreadyExistsRecipeLikeException(
                RecipeLikeErrorCode.ALREADY_EXIST_RECIPELIKE);
        }

        RecipeLike recipeLike = recipeLikeEntityMapper.toRecipeLike(user, recipe);
        recipeLikeRepository.save(recipeLike);
    }

    @Override
    public void delete(User user, Long recipeId) {
        RecipeLike recipeLike = recipeLikeRepository.findByUserIdAndRecipeId(user.getId(), recipeId)
            .orElseThrow(
                () -> new NotFoundRecipeLikeException(RecipeLikeErrorCode.NOT_FOUND_RECIPELIKE));
        recipeLikeRepository.delete(recipeLike);
    }
}

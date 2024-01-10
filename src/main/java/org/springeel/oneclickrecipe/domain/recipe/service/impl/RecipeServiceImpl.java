package org.springeel.oneclickrecipe.domain.recipe.service.impl;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.RecipeCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.RecipeDeleteServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.recipe.mapper.entity.RecipeEntityMapper;
import org.springeel.oneclickrecipe.domain.recipe.repository.RecipeRepository;
import org.springeel.oneclickrecipe.domain.recipe.service.RecipeService;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springeel.oneclickrecipe.domain.user.repository.UserRepository;
import org.springeel.oneclickrecipe.global.exception.ErrorCode;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeEntityMapper recipeEntityMapper;

    public void createRecipe(final RecipeCreateServiceRequestDto requestDto, User user) {
        Recipe recipe = recipeEntityMapper.toRecipe(requestDto, user);
        recipeRepository.save(recipe);
    }

//    public void deleteRecipe(final RecipeDeleteServiceRequestDto requestDto, Long userId) {
//        Recipe recipe = recipeRepository.findRecipeByIdAndUser_Id(requestDto.recipeId(), userId);
//        if (recipe == null) {
//            throw new RuntimeException("recipe 값이 없습니다");
//        }
//        recipeRepository.delete(recipe);
//    }

    public void deleteRecipe(final RecipeDeleteServiceRequestDto requestDto, User user) {
        Recipe recipe = recipeRepository.findRecipeByIdAndUser_Id(
            requestDto.recipeId(),
            user.getId());
        if (recipe == null) {
            throw new RuntimeException("recipe 값이 없습니다");
        }
        recipeRepository.delete(recipe);
    }
}

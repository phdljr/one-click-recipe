package org.springeel.oneclickrecipe.domain.recipe.repository;

import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Recipe findRecipeByIdAndUser_Id(Long recipeId, Long userId);
}

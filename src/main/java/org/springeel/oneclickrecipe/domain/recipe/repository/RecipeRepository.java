package org.springeel.oneclickrecipe.domain.recipe.repository;

import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findByRecipeId(Long recipeId);


}

package org.springeel.oneclickrecipe.domain.recipeprocess.repository;

import java.util.Optional;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.recipeprocess.entity.RecipeProcess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeProcessRepository extends JpaRepository<RecipeProcess, Long> {

    Optional<RecipeProcess> findByIdAndRecipe(Long recipeId, Recipe recipe);

}

package org.springeel.oneclickrecipe.domain.recipefood.repository;

import java.util.Optional;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.recipefood.entity.RecipeFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeFoodRepository extends JpaRepository<RecipeFood, Long> {

    Optional<RecipeFood> findByIdAndRecipe(Long recipeFoodId, Recipe recipe);

}

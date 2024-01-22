package org.springeel.oneclickrecipe.domain.recipeprocess.repository;

import java.util.List;
import java.util.Optional;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.recipeprocess.entity.RecipeProcess;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeProcessRepository extends JpaRepository<RecipeProcess, Long> {

    Optional<RecipeProcess> findByIdAndRecipe_User(Long recipeId, User user);

    List<RecipeProcess> findAllByRecipeOrderBySequenceAsc(Recipe recipe);

    RecipeProcess findByRecipeAndSequence(Recipe recipe, Byte sequence);
}

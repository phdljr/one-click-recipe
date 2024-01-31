package org.springeel.oneclickrecipe.domain.recipe.repository;

import java.util.Optional;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Optional<Recipe> findByIdAndUser(Long recipeId, User user);

    Slice<Recipe> findAllSliceBy(Pageable pageable);
}

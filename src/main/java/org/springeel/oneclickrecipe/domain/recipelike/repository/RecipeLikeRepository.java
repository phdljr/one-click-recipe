package org.springeel.oneclickrecipe.domain.recipelike.repository;

import java.util.Optional;
import org.springeel.oneclickrecipe.domain.recipelike.entity.RecipeLike;
import org.springeel.oneclickrecipe.domain.recipelike.entity.RecipeLikeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeLikeRepository extends JpaRepository<RecipeLike, RecipeLikeId> {

    boolean existsByUserIdAndRecipeId(Long userId, Long recipeId);

    Optional<RecipeLike> findByUserIdAndRecipeId(Long userId, Long recipeId);

    long countByRecipeId(Long recipeId);
}


package org.springeel.oneclickrecipe.domain.recipelike.repository;

import org.springeel.oneclickrecipe.domain.recipelike.entity.RecipeLike;
import org.springeel.oneclickrecipe.domain.recipelike.entity.RecipeLikeId;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeLikeRepository extends JpaRepository<RecipeLike, RecipeLikeId> {
    boolean existsByUserIdAndRecipeId(Long userId, Long recipeId);

    Optional<RecipeLike> findByIdAndUser(User user, Long recipeId);
}

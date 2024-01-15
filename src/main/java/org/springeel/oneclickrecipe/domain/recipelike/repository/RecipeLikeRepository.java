package org.springeel.oneclickrecipe.domain.recipelike.repository;

import org.springeel.oneclickrecipe.domain.recipelike.entity.RecipeLike;
import org.springeel.oneclickrecipe.domain.recipelike.entity.RecipeLikeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeLikeRepository extends JpaRepository<RecipeLike, RecipeLikeId> {
}

package org.springeel.oneclickrecipe.domain.review.repository;

import java.util.List;
import java.util.Optional;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.review.entity.Review;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findByIdAndUser(Long recipeId, User user);

    List<Review> findAllByRecipeId(Long recipeId);

    Slice<Review> findAllSliceByRecipeId(Long recipeId, Pageable pageable);
}

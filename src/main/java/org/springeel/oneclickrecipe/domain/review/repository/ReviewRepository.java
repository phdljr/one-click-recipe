package org.springeel.oneclickrecipe.domain.review.repository;

import org.springeel.oneclickrecipe.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}

package org.springeel.oneclickrecipe.domain.review.service.impl;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.recipe.repository.RecipeRepository;
import org.springeel.oneclickrecipe.domain.review.dto.service.ReviewCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.review.dto.service.ReviewUpdateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.review.entity.Review;
import org.springeel.oneclickrecipe.domain.review.exception.NotFoundReviewException;
import org.springeel.oneclickrecipe.domain.review.exception.ReviewErrorCode;
import org.springeel.oneclickrecipe.domain.review.mapper.entity.ReviewEntityMapper;
import org.springeel.oneclickrecipe.domain.review.repository.ReviewRepository;
import org.springeel.oneclickrecipe.domain.review.service.ReviewService;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final RecipeRepository recipeRepository;
    private final ReviewEntityMapper reviewEntityMapper;


    @Override
    public void createReview(User user, ReviewCreateServiceRequestDto serviceRequestDto, Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(NullPointerException::new);
        Review review = reviewEntityMapper.toReview(serviceRequestDto, user, recipe);
        reviewRepository.save(review);
    }

    public void updateReview(User user, ReviewUpdateServiceRequestDto serviceRequestDto, Long recipeId) {

        Review review = reviewRepository.findById(recipeId)
            .orElseThrow(() -> new NotFoundReviewException(ReviewErrorCode.NOT_FOUND_REVIEW));

        review.update(review.getContent(), review.getStar());
    }
}
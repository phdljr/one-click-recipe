package org.springeel.oneclickrecipe.domain.review.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.recipe.exception.NotFoundRecipeException;
import org.springeel.oneclickrecipe.domain.recipe.exception.RecipeErrorCode;
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
        Recipe recipe = recipeRepository.findById(recipeId)
            .orElseThrow(() -> new NotFoundRecipeException(RecipeErrorCode.NOT_FOUND_RECIPE));
        Review review = reviewEntityMapper.toReview(serviceRequestDto, user, recipe);
        reviewRepository.save(review);
    }

    @Transactional
    @Override
    public void updateReview(Long reviewId, User user, ReviewUpdateServiceRequestDto serviceRequestDto) {

        Review review = reviewRepository.findByIdAndUser(reviewId, user)
            .orElseThrow(() -> new NotFoundReviewException(ReviewErrorCode.NOT_FOUND_REVIEW));

        review.update(serviceRequestDto.content(), serviceRequestDto.star());
        reviewRepository.save(review);
    }

    @Override
    public void deleteReview(User user, Long reviewId) {

        Review review = reviewRepository.findByIdAndUser(reviewId, user)
            .orElseThrow(() -> new NotFoundReviewException(ReviewErrorCode.NOT_FOUND_REVIEW));
        reviewRepository.delete(review);
    }

    @Override
    public void getReview(User user, Long reviewId) {
        
    }
}
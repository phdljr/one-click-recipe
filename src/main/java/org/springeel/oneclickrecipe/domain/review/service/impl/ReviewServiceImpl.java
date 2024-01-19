package org.springeel.oneclickrecipe.domain.review.service.impl;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.recipe.exception.NotFoundRecipeException;
import org.springeel.oneclickrecipe.domain.recipe.exception.RecipeErrorCode;
import org.springeel.oneclickrecipe.domain.recipe.repository.RecipeRepository;
import org.springeel.oneclickrecipe.domain.review.dto.service.ReviewCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.review.dto.service.ReviewReadResponseDto;
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
    public void createReview(User user, ReviewCreateServiceRequestDto serviceRequestDto,
        Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
            .orElseThrow(() -> new NotFoundRecipeException(RecipeErrorCode.NOT_FOUND_RECIPE));
        Review review = reviewEntityMapper.toReview(serviceRequestDto, user, recipe);
        reviewRepository.save(review);
    }

    @Transactional
    @Override
    public void updateReview(Long reviewId, User user,
        ReviewUpdateServiceRequestDto serviceRequestDto) {

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
    public List<ReviewReadResponseDto> getReviews(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
            .orElseThrow(() -> new NotFoundRecipeException(RecipeErrorCode.NOT_FOUND_RECIPE));
        // 특정 레시피에 대한 리뷰 목록 조회
        List<Review> reviews = reviewRepository.findByRecipeId(recipeId);

        //  댓글 목록을 Dto로 변환해서 반환
        return reviews.stream()
            .map(review -> ReviewReadResponseDto.builder()
                .content(review.getContent())
                .star(review.getStar())
                .build())
            .toList();

    }
}
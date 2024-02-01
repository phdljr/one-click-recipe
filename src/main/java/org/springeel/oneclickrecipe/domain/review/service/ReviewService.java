package org.springeel.oneclickrecipe.domain.review.service;

import java.util.List;
import org.springeel.oneclickrecipe.domain.review.dto.service.request.ReviewCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.review.dto.service.request.ReviewUpdateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.review.dto.service.response.ReviewCreateResponseDto;
import org.springeel.oneclickrecipe.domain.review.dto.service.response.ReviewReadResponseDto;
import org.springeel.oneclickrecipe.domain.user.entity.User;

public interface ReviewService {

    ReviewCreateResponseDto createReview(User user,
        ReviewCreateServiceRequestDto controllerRequestDto, Long recipeId);

    void updateReview(Long reviewId, User user, ReviewUpdateServiceRequestDto serviceRequestDto);

    void deleteReview(User user, Long reviewId);

    List<ReviewReadResponseDto> getReviews(Long recipeId, final Integer page);
}

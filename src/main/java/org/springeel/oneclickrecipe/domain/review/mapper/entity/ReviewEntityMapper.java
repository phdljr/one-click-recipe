package org.springeel.oneclickrecipe.domain.review.mapper.entity;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.review.dto.service.ReviewCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.review.dto.service.ReviewReadResponseDto;
import org.springeel.oneclickrecipe.domain.review.entity.Review;
import org.springeel.oneclickrecipe.domain.user.entity.User;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReviewEntityMapper {
    List<ReviewReadResponseDto> toReviewResponseDtos(List<Review> review);

    ReviewReadResponseDto toReviewReadResponseDto(Review review);

    Review toReview(ReviewCreateServiceRequestDto reviewRequestDto, User user, Recipe RecipeId);

}
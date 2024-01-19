package org.springeel.oneclickrecipe.domain.review.mapper.entity;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.review.dto.service.ReviewCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.review.dto.service.ReviewReadResponseDto;
import org.springeel.oneclickrecipe.domain.review.entity.Review;
import org.springeel.oneclickrecipe.domain.user.entity.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReviewEntityMapper {

    List<ReviewReadResponseDto> toReviewResponseDtos(List<Review> review);

    ReviewReadResponseDto toReviewReadResponseDto(Review review);

    @Mapping(source = "user", target = "user")
    @Mapping(source = "recipe", target = "recipe")
    Review toReview(ReviewCreateServiceRequestDto reviewRequestDto, User user, Recipe recipe);

}
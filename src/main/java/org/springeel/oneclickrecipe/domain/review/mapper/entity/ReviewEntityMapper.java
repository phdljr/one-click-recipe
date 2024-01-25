package org.springeel.oneclickrecipe.domain.review.mapper.entity;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.review.dto.service.request.ReviewCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.review.dto.service.response.ReviewReadResponseDto;
import org.springeel.oneclickrecipe.domain.review.entity.Review;
import org.springeel.oneclickrecipe.domain.user.entity.User;

@Mapper(componentModel = SPRING)
public interface ReviewEntityMapper {

    List<ReviewReadResponseDto> toReviewReadResponseDtos(List<Review> reviews);

    @Mapping(source = "user.nickname", target = "writer")
    ReviewReadResponseDto toReviewReadResponseDto(Review review);

    @Mapping(source = "user", target = "user")
    @Mapping(source = "recipe", target = "recipe")
    Review toReview(ReviewCreateServiceRequestDto reviewRequestDto, User user, Recipe recipe);

}
package org.springeel.oneclickrecipe.domain.review.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import org.springeel.oneclickrecipe.domain.review.dto.controller.ReviewCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.review.dto.controller.ReviewUpdateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.review.dto.service.ReviewCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.review.dto.service.ReviewReadResponseDto;
import org.springeel.oneclickrecipe.domain.review.dto.service.ReviewUpdateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.review.entity.Review;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReviewMapper {

    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    Review toReview(ReviewCreateServiceRequestDto reviewRequestDto);

    ReviewCreateServiceRequestDto toReviewCreateServiceRequestDto(
        ReviewCreateControllerRequestDto controllerRequestDto);

    ReviewReadResponseDto toReviewReadResponseDto(Review review);

    ReviewUpdateServiceRequestDto toReviewUpdateServiceRequestDto(
        ReviewUpdateControllerRequestDto updateControllerRequestDto
    );

    List<ReviewReadResponseDto> toReviewResponseDtos(List<Review> review);
}

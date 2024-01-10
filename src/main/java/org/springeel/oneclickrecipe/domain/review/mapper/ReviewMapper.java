package org.springeel.oneclickrecipe.domain.review.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springeel.oneclickrecipe.domain.review.dto.controller.ReviewCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.review.dto.service.ReviewCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.review.entity.Review;

@Mapper
public interface ReviewMapper {

    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    Review toReview(ReviewCreateServiceRequestDto reviewRequestDto);

    ReviewCreateServiceRequestDto toReviewServiceRequestDto(
        ReviewCreateControllerRequestDto controllerRequestDto);
}

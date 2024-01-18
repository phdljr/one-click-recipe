package org.springeel.oneclickrecipe.domain.review.mapper.dto;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springeel.oneclickrecipe.domain.review.dto.controller.ReviewCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.review.dto.controller.ReviewUpdateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.review.dto.service.ReviewCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.review.dto.service.ReviewUpdateServiceRequestDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReviewDtoMapper {
    ReviewCreateServiceRequestDto toReviewCreateServiceRequestDto(
        ReviewCreateControllerRequestDto controllerRequestDto);

    ReviewUpdateServiceRequestDto toReviewUpdateServiceRequestDto(
        ReviewUpdateControllerRequestDto updateControllerRequestDto);

}
package org.springeel.oneclickrecipe.domain.review.mapper.dto;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import org.mapstruct.Mapper;
import org.springeel.oneclickrecipe.domain.review.dto.controller.ReviewCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.review.dto.controller.ReviewUpdateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.review.dto.service.request.ReviewCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.review.dto.service.request.ReviewUpdateServiceRequestDto;

@Mapper(componentModel = SPRING)
public interface ReviewDtoMapper {

    ReviewCreateServiceRequestDto toReviewCreateServiceRequestDto(
        ReviewCreateControllerRequestDto controllerRequestDto);

    ReviewUpdateServiceRequestDto toReviewUpdateServiceRequestDto(
        ReviewUpdateControllerRequestDto updateControllerRequestDto);

}
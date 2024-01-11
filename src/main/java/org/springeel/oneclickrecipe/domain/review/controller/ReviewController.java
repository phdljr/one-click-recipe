package org.springeel.oneclickrecipe.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.review.dto.controller.ReviewCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.review.dto.controller.ReviewUpdateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.review.dto.service.ReviewCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.review.dto.service.ReviewUpdateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.review.mapper.dto.ReviewDtoMapper;
import org.springeel.oneclickrecipe.domain.review.service.ReviewService;
import org.springeel.oneclickrecipe.global.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/recipes")
public class ReviewController {

    private final ReviewDtoMapper reviewDtoMapper;
    private final ReviewService reviewService;

    @PostMapping("/{recipeId}/reviews") //후기작성
    public void create(
        @RequestBody ReviewCreateControllerRequestDto createControllerRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @PathVariable(name = "recipeId") Long recipeId
    ) {
        ReviewCreateServiceRequestDto serviceRequestDto =
            reviewDtoMapper.toReviewCreateServiceRequestDto(createControllerRequestDto);
        reviewService.createReview(userDetails.user(), serviceRequestDto, recipeId);
    }

    @PutMapping("/{recipeId}/reviews/{reviewId}") //후기수정
    public ResponseEntity<?> update(
        @PathVariable(name = "recipeId") Long recipeId,
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @RequestBody ReviewUpdateControllerRequestDto ReviewUpdateServiceRequestDto
    ) {
        ReviewUpdateServiceRequestDto serviceRequestDto =
            reviewDtoMapper.toReviewUpdateServiceRequestDto(ReviewUpdateServiceRequestDto);
        reviewService.updateReview(userDetails.user(), serviceRequestDto, recipeId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}






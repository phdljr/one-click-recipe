package org.springeel.oneclickrecipe.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.review.dto.controller.ReviewCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.review.dto.controller.ReviewUpdateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.review.dto.service.ReviewCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.review.dto.service.ReviewReadResponseDto;
import org.springeel.oneclickrecipe.domain.review.dto.service.ReviewUpdateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.review.mapper.dto.ReviewDtoMapper;
import org.springeel.oneclickrecipe.domain.review.service.ReviewService;
import org.springeel.oneclickrecipe.global.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ReviewController {

    private final ReviewDtoMapper reviewDtoMapper;
    private final ReviewService reviewService;

    @PostMapping("/recipes/{recipeId}/reviews") //후기작성
    public void create(
        @PathVariable(name = "recipeId") Long recipeId,
        @RequestBody ReviewCreateControllerRequestDto createControllerRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        ReviewCreateServiceRequestDto serviceRequestDto =
            reviewDtoMapper.toReviewCreateServiceRequestDto(createControllerRequestDto);
        reviewService.createReview(userDetails.user(), serviceRequestDto, recipeId);
    }

    @PutMapping("/reviews/{reviewId}") //후기수정
    public ResponseEntity<?> update(
        @PathVariable(name = "reviewId") Long reviewId,
        @RequestBody ReviewUpdateControllerRequestDto ReviewUpdateServiceRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        ReviewUpdateServiceRequestDto serviceRequestDto =
            reviewDtoMapper.toReviewUpdateServiceRequestDto(ReviewUpdateServiceRequestDto);
        reviewService.updateReview(reviewId, userDetails.user(), serviceRequestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/reviews/{reviewId}") //후기삭제
    public ResponseEntity<Void> delete(
        @PathVariable(name = "reviewId") Long reviewId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        reviewService.deleteReview(userDetails.user(), reviewId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 특정 레시피의 리뷰 목록 조회
    @GetMapping("/recipes/{recipeId}/reviews") //모든 후기조회
    public ResponseEntity<List<ReviewReadResponseDto>> getReviews(
        @PathVariable(name = "recipeId") Long recipeId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        List<ReviewReadResponseDto> reviews = reviewService.getReviews(userDetails.user(), recipeId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}








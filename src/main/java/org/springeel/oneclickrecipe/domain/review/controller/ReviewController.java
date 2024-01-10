package org.springeel.oneclickrecipe.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.review.dto.controller.ReviewCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.review.dto.service.ReviewCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.review.mapper.ReviewMapper;
import org.springeel.oneclickrecipe.domain.review.service.ReviewService;
import org.springeel.oneclickrecipe.global.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/recipes")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("{recipeId}/reviews") //후기작성
    public void create(
        @RequestBody ReviewCreateControllerRequestDto controllerRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @PathVariable(name = "recipeId") Long recipeId
    ) {
        ReviewCreateServiceRequestDto serviceRequestDto =
            ReviewMapper.INSTANCE.toReviewServiceRequestDto(controllerRequestDto);
        reviewService.createReview(userDetails.getUser(), serviceRequestDto, recipeId);

    }

}

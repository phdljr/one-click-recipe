package org.springeel.oneclickrecipe.domain.recipelike.controller;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.recipelike.service.RecipeLikeService;
import org.springeel.oneclickrecipe.global.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/recipes")
@RestController
public class RecipeLikeController {

    private final RecipeLikeService recipeLikeService;

    @PostMapping("/{recipeId}/likes") //좋아요 생성
    public ResponseEntity<Void> create(
        @PathVariable(name = "recipeId") Long recipeId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        recipeLikeService.create(userDetails.user(), recipeId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{recipeId}/likes") //좋아요 삭제
    public ResponseEntity<Void> delete(
        @PathVariable(name = "recipeId") Long recipeId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        recipeLikeService.delete(userDetails.user(), recipeId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{recipeId}/likes/count") // 특정 레시피 좋아요 수 조회
    public ResponseEntity<Long> getLikesCount(
        @PathVariable(name = "recipeId") Long recipeId
    ) {
        long likesCount = recipeLikeService.getLikesCount(recipeId);
        return new ResponseEntity<>(likesCount, HttpStatus.OK);
    }

    @GetMapping("/{recipeId}/likes/status") // 사용자가 특정 레시피에 좋아요를 눌렀는 지 여부
    public ResponseEntity<Boolean> getUserLikeStatus(
        @PathVariable(name = "recipeId") Long recipeId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        boolean likeStatus = recipeLikeService.getUserLikeStatus(userDetails.user(), recipeId);
        return new ResponseEntity<>(likeStatus, HttpStatus.OK);
    }
}

package org.springeel.oneclickrecipe.domain.recipelike.controller;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.recipelike.service.RecipeLikeService;
import org.springeel.oneclickrecipe.global.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
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
}

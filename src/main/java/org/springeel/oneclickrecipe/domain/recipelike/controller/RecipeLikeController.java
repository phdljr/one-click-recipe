package org.springeel.oneclickrecipe.domain.recipelike.controller;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.recipelike.dto.controller.RecipeLikeCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.recipelike.dto.service.RecipeLikeCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipelike.mapper.dto.RecipeLikeDtoMapper;
import org.springeel.oneclickrecipe.domain.recipelike.service.RecipeLikeService;
import org.springeel.oneclickrecipe.global.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/recipes")
public class RecipeLikeController {

    private final RecipeLikeDtoMapper recipeLikeDtoMapper;
    private final RecipeLikeService recipeLikeService;

    @PostMapping("/{recipeId}/likes") //좋아요 생성
    public void create(
        @PathVariable(name = "recipeId") Long recipeId,
        @RequestBody RecipeLikeCreateControllerRequestDto createControllerRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        RecipeLikeCreateServiceRequestDto serviceRequestDto =
            recipeLikeDtoMapper.toRecipeLikeCreateServiceRequestDto(createControllerRequestDto);
        recipeLikeService.create(userDetails.user(), serviceRequestDto, recipeId);
    }

    @DeleteMapping("/{recipeId}/likes") //좋아요 삭제
    public ResponseEntity<Void> delete(
        @PathVariable(name = "recipeId") Long recipeId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        recipeLikeService.deleteRecipeLike(userDetails.user(), recipeId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

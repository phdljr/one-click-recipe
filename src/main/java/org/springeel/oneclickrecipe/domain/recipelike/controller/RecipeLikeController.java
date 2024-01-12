package org.springeel.oneclickrecipe.domain.recipelike.controller;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.recipelike.service.RecipeLikeService;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/recipes")
public class RecipeLikeController {

    private final RecipeLikeService recipeLikeService;

    @PatchMapping("/{recipeId}/likes") //좋아요 생성,삭제
    public void manageRecipeLike() {
        
    }

}

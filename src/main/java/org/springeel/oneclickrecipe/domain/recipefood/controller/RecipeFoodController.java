package org.springeel.oneclickrecipe.domain.recipefood.controller;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.recipefood.dto.controller.RecipeFoodCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.recipefood.dto.service.RecipeFoodCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipefood.mapper.dto.RecipeFoodDtoMapper;
import org.springeel.oneclickrecipe.domain.recipefood.service.RecipeFoodService;
import org.springeel.oneclickrecipe.global.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class RecipeFoodController {

    private final RecipeFoodService recipeFoodService;
    private final RecipeFoodDtoMapper recipeFoodDtoMapper;

    @PostMapping("{recipeId}/recipeFoods")
    public ResponseEntity<?> create(
        @RequestBody RecipeFoodCreateControllerRequestDto requestDto,
        @PathVariable Long recipeId) {
        RecipeFoodCreateServiceRequestDto serviceRequestDto =
            recipeFoodDtoMapper.toRecipeFoodCreateServiceRequestDto(requestDto);
        recipeFoodService.createRecipeFood(serviceRequestDto, recipeId);
        return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK);
    }

    @DeleteMapping("{recipeId}/recipeFoods/{recipeFoodId}")
    public ResponseEntity<?> delete(
        @PathVariable Long recipeId,
        @PathVariable Long recipeFoodId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        recipeFoodService.deleteRecipeFood(recipeId, recipeFoodId, userDetails.user());
        return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK);
    }

}

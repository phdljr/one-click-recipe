package org.springeel.oneclickrecipe.domain.recipe.controller;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.recipe.dto.controller.RecipeCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.controller.RecipeUpdateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.RecipeCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.RecipeUpdateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.mapper.dto.RecipeDtoMapper;
import org.springeel.oneclickrecipe.domain.recipe.service.RecipeService;
import org.springeel.oneclickrecipe.global.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class RecipeController {

    private final RecipeService recipeService;
    private final RecipeDtoMapper recipeDtoMapper;

    @PostMapping("/{userId}/recipes")
    public ResponseEntity<?> create(
        @RequestBody RecipeCreateControllerRequestDto controllerRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        RecipeCreateServiceRequestDto serviceRequestDto =
            recipeDtoMapper.toRecipeServiceRequestDto(controllerRequestDto);
        recipeService.createRecipe(serviceRequestDto, userDetails.user());
        return ResponseEntity.status(HttpStatus.CREATED).body("레시피 생성 완성");
    }

    @DeleteMapping("/{userId}/{recipeId}")
    public ResponseEntity<?> delete(
        @PathVariable Long recipeId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        recipeService.deleteRecipe(recipeId, userDetails.user());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/recipes/{recipeId}")
    public ResponseEntity<?> update(
        @RequestBody RecipeUpdateControllerRequestDto controllerRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @PathVariable Long recipeId
    ) {
        RecipeUpdateServiceRequestDto serviceRequestDto =
            recipeDtoMapper.toRecipeUpdateServiceRequestDto(controllerRequestDto);
        recipeService.updateRecipe(serviceRequestDto, userDetails.user(), recipeId);
        return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK);
    }
}

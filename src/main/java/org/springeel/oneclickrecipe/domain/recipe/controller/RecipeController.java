package org.springeel.oneclickrecipe.domain.recipe.controller;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.recipe.dto.controller.RecipeCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.controller.RecipeDeleteControllerRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.RecipeCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.RecipeDeleteServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.mapper.dto.RecipeDtoMapper;
import org.springeel.oneclickrecipe.domain.recipe.service.RecipeService;
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
public class RecipeController {

    private final RecipeService recipeService;
    private final RecipeDtoMapper recipeDtoMapper;

    @PostMapping("/recipes")
    public ResponseEntity<?> create(
        @RequestBody RecipeCreateControllerRequestDto controllerRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        RecipeCreateServiceRequestDto serviceRequestDto =
            recipeDtoMapper.toRecipeServiceRequestDto(controllerRequestDto);
        recipeService.createRecipe(serviceRequestDto, userDetails.user());
        return ResponseEntity.status(HttpStatus.CREATED).body("레시피 생성 완성");
    }

    @DeleteMapping("/{userId}/recipes")
    public ResponseEntity<?> delete(
        @RequestBody RecipeDeleteControllerRequestDto controllerRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        RecipeDeleteServiceRequestDto serviceRequestDto =
            recipeDtoMapper.toRecipeServiceDeleteRequestDto(controllerRequestDto);
        recipeService.deleteRecipe(serviceRequestDto, userDetails.user());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

package org.springeel.oneclickrecipe.domain.recipe.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.recipe.dto.controller.RecipeCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.controller.RecipeUpdateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.request.RecipeCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.request.RecipeUpdateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.response.RecipeAllReadResponseDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.response.RecipeReadResponseDto;
import org.springeel.oneclickrecipe.domain.recipe.mapper.dto.RecipeDtoMapper;
import org.springeel.oneclickrecipe.domain.recipe.service.RecipeService;
import org.springeel.oneclickrecipe.global.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/recipes")
@RestController
public class RecipeController {

    private final RecipeService recipeService;
    private final RecipeDtoMapper recipeDtoMapper;

    @PostMapping
    public ResponseEntity<?> create(
        @RequestBody RecipeCreateControllerRequestDto controllerRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        RecipeCreateServiceRequestDto serviceRequestDto =
            recipeDtoMapper.toRecipeCreateServiceRequestDto(controllerRequestDto);
        recipeService.createRecipe(serviceRequestDto, userDetails.user());
        return ResponseEntity.status(HttpStatus.CREATED).body("레시피 생성 완성");
    }

    @DeleteMapping("/{recipeId}")
    public ResponseEntity<?> delete(
        @PathVariable Long recipeId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        recipeService.deleteRecipe(recipeId, userDetails.user());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{recipeId}")
    public ResponseEntity<?> update(
        @RequestBody RecipeUpdateControllerRequestDto controllerRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @PathVariable Long recipeId
    ) {
        RecipeUpdateServiceRequestDto serviceRequestDto =
            recipeDtoMapper.toRecipeUpdateServiceRequestDto(controllerRequestDto);
        recipeService.updateRecipe(serviceRequestDto, userDetails.user(), recipeId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<?> readAll() {
        List<RecipeAllReadResponseDto> recipeAllReadResponseDto =
            recipeService.readAllRecipe();
        return ResponseEntity.status(HttpStatus.OK).body(recipeAllReadResponseDto);
    }

    @GetMapping("/{recipeId}")
    public ResponseEntity<?> readOnce(
        @PathVariable Long recipeId
    ) {
        RecipeReadResponseDto readResponseDto =
            recipeService.readRecipe(recipeId);
        return ResponseEntity.status(HttpStatus.OK).body(readResponseDto);

    }
}

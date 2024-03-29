package org.springeel.oneclickrecipe.domain.recipe.controller;

import jakarta.validation.Valid;
import java.io.IOException;
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
import org.springeel.oneclickrecipe.domain.recipefood.dto.controller.RecipeFoodCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.recipefood.dto.service.request.RecipeFoodCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipefood.mapper.dto.RecipeFoodDtoMapper;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.controller.RecipeProcessCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.service.request.RecipeProcessCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipeprocess.mapper.dto.RecipeProcessDtoMapper;
import org.springeel.oneclickrecipe.global.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RequestMapping("/api/v1/recipes")
@RestController
public class RecipeController {

    private final RecipeService recipeService;
    private final RecipeDtoMapper recipeDtoMapper;
    private final RecipeFoodDtoMapper recipeFoodDtoMapper;
    private final RecipeProcessDtoMapper recipeProcessDtoMapper;

    @PostMapping
    public ResponseEntity<?> create(
        @Valid @RequestPart(name = "recipeCreateRequestDto") RecipeCreateControllerRequestDto recipeControllerRequestDto,
        @RequestPart(name = "recipeCreateImage") MultipartFile recipeImage,
        @Valid @RequestPart(name = "recipeFoodCreateRequestDto") List<RecipeFoodCreateControllerRequestDto> recipeFoodControllerRequestDto,
        @Valid @RequestPart(name = "recipeProcessCreateRequestDto") List<RecipeProcessCreateControllerRequestDto> recipeProcessControllerRequestDto,
        @RequestPart(name = "recipeProcessCreateImage") List<MultipartFile> recipeProcessImage,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) throws IOException {
        RecipeCreateServiceRequestDto recipeCreateServiceRequestDto =
            recipeDtoMapper.toRecipeCreateServiceRequestDto(recipeControllerRequestDto);
        List<RecipeFoodCreateServiceRequestDto> recipeFoodCreateServiceRequestDtos =
            recipeFoodDtoMapper.toRecipeFoodCreateServiceRequestDtos(
                recipeFoodControllerRequestDto);
        List<RecipeProcessCreateServiceRequestDto> recipeProcessCreateServiceRequestDtos =
            recipeProcessDtoMapper.toRecipeProcessCreateServiceRequestDtos(
                recipeProcessControllerRequestDto);

        recipeService.createRecipe(recipeCreateServiceRequestDto, recipeImage,
            recipeFoodCreateServiceRequestDtos, recipeProcessCreateServiceRequestDtos,
            recipeProcessImage, userDetails.user());
        return ResponseEntity.status(HttpStatus.CREATED).build();
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
        @RequestPart(name = "recipeUpdateRequestDto") RecipeUpdateControllerRequestDto recipeUpdateRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @PathVariable Long recipeId,
        @RequestPart(name = "recipeUpdateImage", required = false) MultipartFile recipeUpdateImage
    ) throws IOException {
        RecipeUpdateServiceRequestDto serviceRequestDto =
            recipeDtoMapper.toRecipeUpdateServiceRequestDto(recipeUpdateRequestDto);
        recipeService.updateRecipe(serviceRequestDto, userDetails.user(), recipeId,
            recipeUpdateImage);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<?> readAll(
        @RequestParam(name = "page", defaultValue = "0") Integer page,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        List<RecipeAllReadResponseDto> recipeAllReadResponseDto =
            recipeService.readAllRecipe(page, userDetails);
        return ResponseEntity.status(HttpStatus.OK).body(recipeAllReadResponseDto);
    }

    @GetMapping("/{recipeId}")
    public ResponseEntity<?> readOnce(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @PathVariable Long recipeId
    ) {
        RecipeReadResponseDto readResponseDto =
            recipeService.readRecipe(recipeId, userDetails);
        return ResponseEntity.status(HttpStatus.OK).body(readResponseDto);
    }
}

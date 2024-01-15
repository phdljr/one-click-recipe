package org.springeel.oneclickrecipe.domain.recipeprocess.controller;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.controller.RecipeProcessCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.controller.RecipeProcessUpdateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.service.RecipeProcessCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.service.RecipeProcessUpdateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipeprocess.mapper.dto.RecipeProcessDtoMapper;
import org.springeel.oneclickrecipe.domain.recipeprocess.service.RecipeProcessService;
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
@RequestMapping("/api/v1/recipes")
public class RecipeProcessController {

    private final RecipeProcessService processService;
    private final RecipeProcessDtoMapper processDtoMapper;

    @PostMapping("/{recipeId}/processes")
    public ResponseEntity<?> create(
        @RequestBody RecipeProcessCreateControllerRequestDto controllerRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @PathVariable Long recipeId
    ) {
        RecipeProcessCreateServiceRequestDto serviceRequestDto =
            processDtoMapper.toRecipeProcessCreateServiceRequestDto(controllerRequestDto);
        processService.createRecipeProcess(serviceRequestDto, userDetails.user(), recipeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(HttpStatus.CREATED);
    }

    @DeleteMapping("/{recipeId}/{processId}")
    public ResponseEntity<?> delete(
        @PathVariable Long recipeId,
        @PathVariable Long processId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        processService.deleteRecipeProcess(recipeId, userDetails.user(), processId);
        return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK);
    }

    @PutMapping("/{recipeId}/{processId}")
    public ResponseEntity<?> update(
        @RequestBody RecipeProcessUpdateControllerRequestDto controllerRequestDto,
        @PathVariable Long recipeId,
        @PathVariable Long processId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        RecipeProcessUpdateServiceRequestDto serviceRequestDto =
            processDtoMapper.toRecipeProcessUpdateServiceRequestDto(controllerRequestDto);
        processService.updateRecipeProcess(serviceRequestDto, recipeId, userDetails.user(),
            processId);
        return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK);
    }
}

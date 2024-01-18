package org.springeel.oneclickrecipe.domain.recipeprocess.controller;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.recipe.repository.RecipeRepository;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.controller.RecipeProcessCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.controller.RecipeProcessUpdateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.service.RecipeProcessCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.service.RecipeProcessUpdateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipeprocess.mapper.dto.RecipeProcessDtoMapper;
import org.springeel.oneclickrecipe.domain.recipeprocess.service.RecipeProcessService;
import org.springeel.oneclickrecipe.global.security.UserDetailsImpl;
import org.springeel.oneclickrecipe.global.util.S3Provider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/recipes")
public class RecipeProcessController {

    private final RecipeProcessService processService;
    private final RecipeProcessDtoMapper processDtoMapper;
    private final S3Provider s3Provider;
    private final RecipeRepository recipeRepository;

    @PostMapping("/{recipeId}/processes")
    public ResponseEntity<?> create(
        @RequestPart RecipeProcessCreateControllerRequestDto controllerRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @PathVariable Long recipeId,
        @RequestPart MultipartFile multipartFile
    ) throws IOException {
        RecipeProcessCreateServiceRequestDto serviceRequestDto =
            processDtoMapper.toRecipeProcessCreateServiceRequestDto(controllerRequestDto);
        processService.createRecipeProcess(serviceRequestDto, userDetails.user(), recipeId,
            multipartFile);
        return ResponseEntity.status(HttpStatus.CREATED).body(HttpStatus.CREATED);
    }

    @DeleteMapping("/{recipeId}/processes/{processId}")
    public ResponseEntity<?> delete(
        @PathVariable Long recipeId,
        @PathVariable Long processId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        processService.deleteRecipeProcess(recipeId, userDetails.user(), processId);
        return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK);
    }

    @PutMapping("/{recipeId}/processes/{processId}")
    public ResponseEntity<?> update(
        @RequestPart RecipeProcessUpdateControllerRequestDto controllerRequestDto,
        @PathVariable Long recipeId,
        @PathVariable Long processId,
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @RequestPart MultipartFile multipartFile
    ) throws IOException {
        RecipeProcessUpdateServiceRequestDto serviceRequestDto =
            processDtoMapper.toRecipeProcessUpdateServiceRequestDto(controllerRequestDto);
        processService.updateRecipeProcess(serviceRequestDto, recipeId, userDetails.user(),
            processId, multipartFile);
        return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK);
    }
}

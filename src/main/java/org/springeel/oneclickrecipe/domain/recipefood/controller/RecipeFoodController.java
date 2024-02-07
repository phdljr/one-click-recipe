package org.springeel.oneclickrecipe.domain.recipefood.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.recipefood.dto.controller.RecipeFoodCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.recipefood.dto.controller.RecipeFoodUpdateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.recipefood.dto.service.request.RecipeFoodCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipefood.dto.service.request.RecipeFoodUpdateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipefood.dto.service.response.RecipeFoodReadResponseDto;
import org.springeel.oneclickrecipe.domain.recipefood.mapper.dto.RecipeFoodDtoMapper;
import org.springeel.oneclickrecipe.domain.recipefood.service.RecipeFoodService;
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
@RequestMapping("/api/v1")
@RestController
public class RecipeFoodController {

    private final RecipeFoodService recipeFoodService;
    private final RecipeFoodDtoMapper recipeFoodDtoMapper;

    @PostMapping("/recipes/{recipeId}/recipe-foods")
    public ResponseEntity<?> create(
        @RequestBody RecipeFoodCreateControllerRequestDto controllerRequestDto,
        @PathVariable Long recipeId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        RecipeFoodCreateServiceRequestDto serviceRequestDto =
            recipeFoodDtoMapper.toRecipeFoodCreateServiceRequestDto(controllerRequestDto);
        recipeFoodService.createRecipeFood(serviceRequestDto, recipeId, userDetails.user());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/recipes/{recipeId}/recipe-foods/all")
    public ResponseEntity<?> create(
        @RequestBody List<RecipeFoodCreateControllerRequestDto> controllerRequestDtos,
        @PathVariable Long recipeId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        List<RecipeFoodCreateServiceRequestDto> serviceRequestDto =
            recipeFoodDtoMapper.toRecipeFoodCreateServiceRequestDtos(controllerRequestDtos);
        recipeFoodService.createRecipeFoodAll(serviceRequestDto, recipeId, userDetails.user());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/recipe-foods/{recipeFoodId}")
    public ResponseEntity<?> delete(
//        @PathVariable Long recipeId,
        @PathVariable Long recipeFoodId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        recipeFoodService.deleteRecipeFood(recipeFoodId, userDetails.user());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/recipe-foods/{recipeFoodId}")
    public ResponseEntity<?> update(
        @PathVariable Long recipeFoodId,
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @RequestBody RecipeFoodUpdateControllerRequestDto controllerRequestDto
    ) {
        RecipeFoodUpdateServiceRequestDto serviceRequestDto =
            recipeFoodDtoMapper.toRecipeFoodUpdateServiceRequestDto(controllerRequestDto);
        recipeFoodService.updateRecipeFood(recipeFoodId, userDetails.user(),
            serviceRequestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/recipes/{recipeId}/recipe-foods")
    public ResponseEntity<?> readRecipeFood(
        @PathVariable Long recipeId
    ) {
        List<RecipeFoodReadResponseDto> responseDto =
            recipeFoodService.readRecipeFood(recipeId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

}


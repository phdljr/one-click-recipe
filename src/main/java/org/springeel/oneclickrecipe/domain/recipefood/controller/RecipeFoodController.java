package org.springeel.oneclickrecipe.domain.recipefood.controller;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.recipefood.dto.controller.RecipeFoodCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.recipefood.dto.service.RecipeFoodCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipefood.mapper.dto.RecipeFoodDtoMapper;
import org.springeel.oneclickrecipe.domain.recipefood.service.RecipeFoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("{recipeId}/recipefood")
    public ResponseEntity<?> create(
        @RequestBody RecipeFoodCreateControllerRequestDto requestDto,
        @PathVariable Long recipeId) {
        RecipeFoodCreateServiceRequestDto serviceRequestDto =
            recipeFoodDtoMapper.toRecipeFoodCreateServiceRequestDto(requestDto);
        recipeFoodService.createRecipeFood(serviceRequestDto, recipeId);
        return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK);
    }

}

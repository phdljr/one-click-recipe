package org.springeel.oneclickrecipe.domain.recipe.controller;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.recipe.dto.controller.RecipeCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.RecipeCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.mapper.RecipeMapper;
import org.springeel.oneclickrecipe.domain.recipe.service.impl.RecipeServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class RecipeController {

    private final RecipeServiceImpl recipeService;

    @PostMapping("/{userId}/recipes")
    public void create(
        @RequestBody RecipeCreateControllerRequestDto controllerRequestDto
    ) {
        RecipeCreateServiceRequestDto serviceRequestDto =
            RecipeMapper.INSTANCE.toRecipeServiceRequestDto(controllerRequestDto);
        recipeService.create(serviceRequestDto);
    }
}

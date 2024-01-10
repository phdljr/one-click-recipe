package org.springeel.oneclickrecipe.domain.recipe.controller;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.recipe.dto.controller.RecipeCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.RecipeCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.mapper.RecipeMapper;
import org.springeel.oneclickrecipe.domain.recipe.service.RecipeService;
import org.springeel.oneclickrecipe.domain.recipe.service.impl.RecipeServiceImpl;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping("/{userId}/recipes")
    public void create(
        @RequestBody RecipeCreateControllerRequestDto controllerRequestDto,
        @PathVariable Long userId) {
        RecipeCreateServiceRequestDto serviceRequestDto =
            RecipeMapper.INSTANCE.toRecipeServiceRequestDto(controllerRequestDto);
        recipeService.createRecipe(serviceRequestDto, userId);
    }
}

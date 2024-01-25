package org.springeel.oneclickrecipe.domain.recipe.service;

import java.io.IOException;
import java.util.List;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.request.RecipeCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.request.RecipeUpdateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.response.RecipeAllReadResponseDto;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.response.RecipeReadResponseDto;
import org.springeel.oneclickrecipe.domain.recipefood.dto.service.request.RecipeFoodCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipeprocess.dto.service.request.RecipeProcessCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface RecipeService {

    void deleteRecipe(Long recipeId, User user);

    void updateRecipe(RecipeUpdateServiceRequestDto requestDto, User user, Long recipeId,
        MultipartFile multipartFile)
        throws IOException;

    List<RecipeAllReadResponseDto> readAllRecipe();

    RecipeReadResponseDto readRecipe(Long recipeId);

    void createRecipe(RecipeCreateServiceRequestDto recipeCreateServiceRequestDto,
        MultipartFile recipeImage,
        List<RecipeFoodCreateServiceRequestDto> recipeFoodCreateServiceRequestDtos,
        List<RecipeProcessCreateServiceRequestDto> recipeProcessCreateServiceRequestDtos,
        List<MultipartFile> recipeProcessImage, User user) throws IOException;
}

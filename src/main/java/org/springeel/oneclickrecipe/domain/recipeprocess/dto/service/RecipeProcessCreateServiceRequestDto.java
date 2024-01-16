package org.springeel.oneclickrecipe.domain.recipeprocess.dto.service;

import org.springframework.web.multipart.MultipartFile;

public record RecipeProcessCreateServiceRequestDto(
    String sequence,
    String description,
    Short time
) {

}

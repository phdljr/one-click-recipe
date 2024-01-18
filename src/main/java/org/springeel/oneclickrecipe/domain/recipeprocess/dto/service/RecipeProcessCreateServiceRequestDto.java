package org.springeel.oneclickrecipe.domain.recipeprocess.dto.service;

import org.springframework.web.multipart.MultipartFile;

public record RecipeProcessCreateServiceRequestDto(
    Byte sequence,
    String description,
    Short time
) {

}

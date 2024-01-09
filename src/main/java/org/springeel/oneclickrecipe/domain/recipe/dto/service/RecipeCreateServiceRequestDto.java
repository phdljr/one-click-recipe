package org.springeel.oneclickrecipe.domain.recipe.dto.service;

import org.springframework.web.multipart.MultipartFile;

public record RecipeCreateServiceRequestDto(
    String title,
    String intro,
    Byte serving,
    String videoPath
) {

}

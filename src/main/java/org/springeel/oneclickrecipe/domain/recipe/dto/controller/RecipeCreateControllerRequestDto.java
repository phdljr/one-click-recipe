package org.springeel.oneclickrecipe.domain.recipe.dto.controller;

import org.springframework.web.multipart.MultipartFile;

public record RecipeCreateControllerRequestDto(
    String title,
    String intro,
    Byte serving,
    String videoPath
) {

}

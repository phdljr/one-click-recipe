package org.springeel.oneclickrecipe.domain.recipelike.service;

import org.springeel.oneclickrecipe.domain.recipelike.dto.service.RecipeLikeCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.user.entity.User;

public interface RecipeLikeService {

    void create(User user, RecipeLikeCreateServiceRequestDto serviceRequestDto, Long recipeId);

    void delete(User user, Long recipeId);
}

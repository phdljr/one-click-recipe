package org.springeel.oneclickrecipe.domain.recipelike.service;

import org.springeel.oneclickrecipe.domain.user.entity.User;

public interface RecipeLikeService {

    void create(User user, Long recipeId);

    void delete(User user, Long recipeId);

    long getLikesCount(Long recipeId);

    boolean getUserLikeStatus(User user, Long recipeId);
}

package org.springeel.oneclickrecipe.domain.recipelike.service.impl;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.recipelike.dto.service.RecipeLikeCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipelike.repository.RecipeLikeRepository;
import org.springeel.oneclickrecipe.domain.recipelike.service.RecipeLikeService;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RecipeLikeServiceImpl implements RecipeLikeService {

    private final RecipeLikeRepository recipeLikeRepository;


    @Override
    public void create(User user, RecipeLikeCreateServiceRequestDto serviceRequestDto, Long recipeId) {

    }
}

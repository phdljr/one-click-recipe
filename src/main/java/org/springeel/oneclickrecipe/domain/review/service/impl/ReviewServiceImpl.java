package org.springeel.oneclickrecipe.domain.review.service.impl;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.recipe.repository.RecipeRepository;
import org.springeel.oneclickrecipe.domain.review.dto.service.ReviewCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.review.dto.service.ReviewUpdateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.review.entity.Review;
import org.springeel.oneclickrecipe.domain.review.repository.ReviewRepository;
import org.springeel.oneclickrecipe.domain.review.service.ReviewService;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final RecipeRepository recipeRepository;

    @Override
    public void createReview(User user, ReviewCreateServiceRequestDto serviceRequestDto, Long recipeId) {

        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(NullPointerException::new); // 후기를 작성할 레시피를 찾는다.(없으면 예외를 던진다)

        Review review = Review.builder()
            .content(serviceRequestDto.content())
            .star(serviceRequestDto.star())
            .user(user)
            .recipe(recipe)
            .build();

        reviewRepository.save(review);
    }

    public void updateReview(User user, ReviewUpdateServiceRequestDto serviceRequestDto, Long recipeId) {

        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(NullPointerException::new); //후기를 수정할 레시피를 찾는다.(없으면 예외 던지기)

        Review review = Review.builder()
            .content(serviceRequestDto.content())
            .star(serviceRequestDto.star())
            .user(user)
            .recipe(recipe)
            .build();
        reviewRepository.save(review);
    }


}

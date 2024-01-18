package org.springeel.oneclickrecipe.domain.review.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.recipe.repository.RecipeRepository;
import org.springeel.oneclickrecipe.domain.review.entity.Review;
import org.springeel.oneclickrecipe.domain.review.repository.ReviewRepository;
import org.springeel.oneclickrecipe.domain.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReviewServiceImplTest {

    @Autowired
    ReviewService reviewService;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    RecipeRepository recipeRepository;


//    @Test
//    void getReviews() {
//    }

    @BeforeEach
    public void getReviews() {

        //given
        Recipe recipe = recipeRepository.save(Recipe.builder()
            .title("스파게티")
            .intro("호로록 짭짭")
            .serving((byte) 4)
            .videoPath("/videos/스파게티.mp4")
            .folderName("스파게티")
            .build());

        Review review1 = reviewRepository.save(Review.builder()
            .content("좋아좋아 아주좋아")
            .star((byte) 5)
            .recipe(recipe)
            .build());

        Review review2 = reviewRepository.save(Review.builder()
            .content("조미료 좀 그만 넣어라")
            .star((byte) 1)
            .recipe(recipe)
            .build());

        Review review3 = reviewRepository.save(Review.builder()
            .content("그냥 평범한 맛이네요")
            .star((byte) 3)
            .recipe(recipe)
            .build());
    }
}
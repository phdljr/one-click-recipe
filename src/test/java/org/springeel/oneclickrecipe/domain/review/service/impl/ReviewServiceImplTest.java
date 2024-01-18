package org.springeel.oneclickrecipe.domain.review.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.recipe.repository.RecipeRepository;
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


    }
}
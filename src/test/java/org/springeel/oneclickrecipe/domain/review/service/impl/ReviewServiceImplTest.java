package org.springeel.oneclickrecipe.domain.review.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.recipe.exception.NotFoundRecipeException;
import org.springeel.oneclickrecipe.domain.recipe.repository.RecipeRepository;
import org.springeel.oneclickrecipe.domain.review.dto.service.response.ReviewReadResponseDto;
import org.springeel.oneclickrecipe.domain.review.entity.Review;
import org.springeel.oneclickrecipe.domain.review.repository.ReviewRepository;
import org.springeel.oneclickrecipe.domain.review.service.ReviewService;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springeel.oneclickrecipe.domain.user.entity.UserRole;
import org.springeel.oneclickrecipe.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class ReviewServiceImplTest {

    @Autowired
    ReviewService reviewService;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("리뷰를 조회해보자")
    @Transactional
    public void getReviews() {

        //given
        User user = userRepository.save(User.builder()
            .email("test@test.test")
            .password("asdasdasdasdsadasweujiofyionusfhuaiosdfa")
            .nickname("testnickname")
            .role(UserRole.USER)
            .build());

        Recipe recipe = recipeRepository.save(Recipe.builder()
            .title("스파게티")
            .user(user)
            .intro("호로록 짭짭")
            .serving((byte) 4)
            .videoUrl("/videos/스파게티.mp4")
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

        //when
        List<ReviewReadResponseDto> result = reviewService.getReviews(recipe.getId());

        //then
        assertThat(result).hasSize(3);
        assertThat(result).extracting("content").contains(
            "좋아좋아 아주좋아",
            "조미료 좀 그만 넣어라",
            "그냥 평범한 맛이네요"
        );
    }

    @Test

    @DisplayName("존재하지 않는 레시피 조회 시 예외발생")
    @Transactional
    public void notFoundReviewTest() {
        assertThatThrownBy(() -> reviewService.getReviews(100L))
            .isInstanceOf(NotFoundRecipeException.class);
    }
}

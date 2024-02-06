package org.springeel.oneclickrecipe.domain.review.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.recipe.exception.NotFoundRecipeException;
import org.springeel.oneclickrecipe.domain.recipe.repository.RecipeRepository;
import org.springeel.oneclickrecipe.domain.review.dto.service.request.ReviewCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.review.dto.service.response.ReviewCreateResponseDto;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


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
    @DisplayName("리뷰조회를 해보자")
    @Transactional
    public void getReviews() {

        //given
        User user = userRepository.save(User.builder()
            .email("test1@test.com")
            .password("12345678")
            .nickname("test1")
            .role(UserRole.USER)
            .build());

        Recipe recipe = recipeRepository.save(Recipe.builder()
            .title("간장계란밥")
            .user(user)
            .intro("황금레시피 간장계란밥")
            .serving((byte) 2)
            .videoUrl("/videos/간장계란밥.mp4")
            .folderName("간장계란밥좋아요")
            .build());

        Review review1 = reviewRepository.save(Review.builder()
            .content("너무 맛있다")
            .star((byte) 5)
            .recipe(recipe)
            .build());

        Review review2 = reviewRepository.save(Review.builder()
            .content("너무 짜요ㅠㅠ")
            .star((byte) 4)
            .recipe(recipe)
            .build());

        Review review3 = reviewRepository.save(Review.builder()
            .content("그냥 평범한 맛이네요")
            .star((byte) 3)
            .recipe(recipe)
            .build());

        //when
        List<ReviewReadResponseDto> result = reviewService.getReviews(recipe.getId(), 0);

        //then
        assertThat(result).hasSize(3);
        assertThat(result).extracting("content").contains(
            "너무 맛있다",
            "너무 짜요ㅠㅠ",
            "그냥 평범한 맛이네요"
        );
    }

    @Test
    @DisplayName("존재하지 않는 레시피 조회 시 예외발생")
    @Transactional
    public void notFoundReviewTest() {
        assertThatThrownBy(() -> reviewService.getReviews(-100L, 0))
            .isInstanceOf(NotFoundRecipeException.class);
    }
    @Test
    @DisplayName("리뷰 작성 테스트")
    @Transactional
    public void createReview() {
        //given
        User user = userRepository.save(User.builder()
            .email("test1@test.com")
            .password("12345678")
            .nickname("test1")
            .role(UserRole.USER)
            .build());

        Recipe recipe = recipeRepository.save(Recipe.builder()
            .title("간장계란밥")
            .user(user)
            .intro("황금레시피 간장계란밥")
            .serving((byte) 2)
            .videoUrl("/videos/간장계란밥.mp4")
            .folderName("간장계란밥좋아요")
            .build());

        ReviewCreateServiceRequestDto requestDto = new ReviewCreateServiceRequestDto("맛있어요!", (byte) 5);

        //when
        ReviewCreateResponseDto createdReview = reviewService.createReview(user, requestDto, recipe.getId());

        //then
        assertThat(createdReview.id()).isNotNull();



    }
}
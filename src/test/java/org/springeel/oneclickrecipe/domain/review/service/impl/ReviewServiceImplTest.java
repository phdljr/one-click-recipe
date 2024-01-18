package org.springeel.oneclickrecipe.domain.review.service.impl;

import org.junit.jupiter.api.Test;
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


    @Test
    void getReviews() {
    }
}
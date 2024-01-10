package org.springeel.oneclickrecipe.domain.review.service.impl;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.review.dto.service.ReviewCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.review.entity.Review;
import org.springeel.oneclickrecipe.domain.review.mapper.ReviewMapper;
import org.springeel.oneclickrecipe.domain.review.repository.ReviewRepository;
import org.springeel.oneclickrecipe.domain.review.service.ReviewService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl extends ReviewService {

    private final ReviewRepository reviewRepository;

    public void create(final ReviewCreateServiceRequestDto reviewRequestDto) {
        Review review = ReviewMapper.INSTANCE.toReview(reviewRequestDto);
        reviewRepository.save(review);
    }
}

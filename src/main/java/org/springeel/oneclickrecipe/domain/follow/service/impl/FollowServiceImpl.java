package org.springeel.oneclickrecipe.domain.follow.service.impl;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.follow.entity.Follow;
import org.springeel.oneclickrecipe.domain.follow.exception.AlreadyExistFollowException;
import org.springeel.oneclickrecipe.domain.follow.exception.FollowErrorCode;
import org.springeel.oneclickrecipe.domain.follow.exception.NotFoundFollowException;
import org.springeel.oneclickrecipe.domain.follow.repository.FollowRepository;
import org.springeel.oneclickrecipe.domain.follow.service.FollowService;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;

    @Override
    public void create(User user, Long userId) {
        Follow follow = followRepository.findByFollowingId(userId)
            .orElseThrow(() -> new NotFoundFollowException(FollowErrorCode.NOT_FOUND_FOLLOW));

        if (followRepository.existsByUserIdAndFollowingId(user.getId(), userId)) {
            throw new AlreadyExistFollowException(
                FollowErrorCode.ALREADY_EXIST_FOLLOW);
        }


    }
}

package org.springeel.oneclickrecipe.domain.follow.service.impl;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.follow.entity.Follow;
import org.springeel.oneclickrecipe.domain.follow.exception.AlreadyExistsFollowException;
import org.springeel.oneclickrecipe.domain.follow.exception.FollowErrorCode;
import org.springeel.oneclickrecipe.domain.follow.exception.NotFoundFollowException;
import org.springeel.oneclickrecipe.domain.follow.mapper.entity.FollowEntityMapper;
import org.springeel.oneclickrecipe.domain.follow.repository.FollowRepository;
import org.springeel.oneclickrecipe.domain.follow.service.FollowService;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springeel.oneclickrecipe.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final FollowEntityMapper followEntityMapper;

    @Override
    public void create(User user, Long followingId) {
        User user1 = userRepository.findById(followingId)
            .orElseThrow(() -> new NotFoundFollowException(FollowErrorCode.NOT_FOUND_FOLLOW));

        if (followRepository.existsByUserIdAndFollowingId(user.getId(), followingId)) {
            throw new AlreadyExistsFollowException(
                FollowErrorCode.ALREADY_EXIST_FOLLOW);
        }
        Follow follow = followEntityMapper.tofollow(user);

    }
}

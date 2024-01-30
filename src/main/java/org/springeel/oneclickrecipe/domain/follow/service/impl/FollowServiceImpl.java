package org.springeel.oneclickrecipe.domain.follow.service.impl;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.follow.entity.Follow;
import org.springeel.oneclickrecipe.domain.follow.exception.AlreadyExistsFollowException;
import org.springeel.oneclickrecipe.domain.follow.exception.FollowErrorCode;
import org.springeel.oneclickrecipe.domain.follow.exception.NotFollowSelfException;
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
        if (user.getId().equals(followingId)) {
            throw new NotFollowSelfException(
                FollowErrorCode.NOT_FOLLOW_SELF);
        }

        User following = userRepository.findById(followingId)
            .orElseThrow(() -> new NotFoundFollowException(FollowErrorCode.NOT_FOUND_FOLLOW));

        if (followRepository.existsByUserIdAndFollowingId(user.getId(), followingId)) {
            throw new AlreadyExistsFollowException(
                FollowErrorCode.ALREADY_EXIST_FOLLOW);
        }
        Follow follow = followEntityMapper.tofollow(user, following);
        followRepository.save(follow);

    }

    @Override
    public void delete(User user, Long followingId) {
        Follow follow = followRepository.findByUserIdAndFollowingId(user.getId(), followingId)
            .orElseThrow(
                () -> new NotFoundFollowException(FollowErrorCode.NOT_FOUND_FOLLOW));
        followRepository.delete(follow);

    }

    @Override
    public long getLikesCount(Long followingId) {
        return followRepository.countByFollowingId(followingId);
    }

    @Override
    public boolean getUserFollowingStatus(User user, Long followingId) {
        return followRepository.existsByUserIdAndFollowingId(user.getId(), followingId);

    }
}

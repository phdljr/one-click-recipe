package org.springeel.oneclickrecipe.domain.follow.service;

import org.springeel.oneclickrecipe.domain.user.entity.User;


public interface FollowService {
    void create(User user, Long followingId);

    void delete(User user, Long followingId);

    long getLikesCount(Long followingId);

    boolean getUserFollowingStatus(User user, Long followingId);
}

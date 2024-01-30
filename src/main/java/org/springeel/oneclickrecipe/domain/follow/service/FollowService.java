package org.springeel.oneclickrecipe.domain.follow.service;

import org.springeel.oneclickrecipe.domain.user.entity.User;


public interface FollowService {
    void create(User user, Long followingId);

    void delete(User user, Long followingId);

    long getFollowCount(Long followingId);

    boolean getUserFollowStatus(User user, Long followingId);
}

package org.springeel.oneclickrecipe.domain.follow.service;

import org.springeel.oneclickrecipe.domain.user.entity.User;

public interface FollowService {
    void create(User user, Long userId);
}

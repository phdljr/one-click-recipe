package org.springeel.oneclickrecipe.domain.follow.service.impl;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.follow.service.FollowService;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FollowServiceImpl implements FollowService {

    @Override
    public void create(User user, Long userId) {

    }
}

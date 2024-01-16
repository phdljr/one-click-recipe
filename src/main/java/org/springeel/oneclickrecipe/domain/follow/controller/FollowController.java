package org.springeel.oneclickrecipe.domain.follow.controller;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.follow.service.FollowService;
import org.springeel.oneclickrecipe.global.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/{userId}") //사용자 구독 생성

public class FollowController {

    private final FollowService followService;

    @PostMapping("/subscribes/{subUserId}")
    public ResponseEntity<Void> create(
        @PathVariable(name = "userId") Long userId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        followService.create(userDetails.user(), userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

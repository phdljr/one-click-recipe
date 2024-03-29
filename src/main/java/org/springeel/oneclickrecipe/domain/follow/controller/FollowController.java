package org.springeel.oneclickrecipe.domain.follow.controller;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.follow.service.FollowService;
import org.springeel.oneclickrecipe.global.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")

public class FollowController {

    private final FollowService followService;

    @PostMapping("/follows/{userId}") //사용자 구독 생성
    public ResponseEntity<?> create(
        @PathVariable(name = "userId") Long followingId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        followService.create(userDetails.user(), followingId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/follows/{userId}") // 사용자 구독 취소
    public ResponseEntity<?> delete(
        @PathVariable(name = "userId") Long followingId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        followService.delete(userDetails.user(), followingId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/follows/{userId}/count") //특정 사용자의 팔로우(구독) 조회
    public ResponseEntity<Long> getFollowCount(
        @PathVariable(name = "userId") Long followingId
    ) {
        long followCount = followService.getFollowCount(followingId);
        return new ResponseEntity<>(followCount, HttpStatus.OK);
    }

    @GetMapping("/follows/{userId}/status") // 사용자가 특정사용자를 구독 했는지 안했는지에 대한 여부 조회
    public ResponseEntity<Boolean> getUserFollowStatus(
        @PathVariable(name = "userId") Long followingId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        boolean followStatus = followService.getUserFollowStatus(userDetails.user(), followingId);
        return new ResponseEntity<>(followStatus, HttpStatus.OK);
    }
}

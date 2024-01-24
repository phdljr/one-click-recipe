package org.springeel.oneclickrecipe.domain.follow.repository;

import org.springeel.oneclickrecipe.domain.follow.entity.Follow;
import org.springeel.oneclickrecipe.domain.follow.entity.FollowId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, FollowId> {
    Optional<Follow> findByUserIdAndFollowingId(Long userId, Long followingId);

    boolean existsByUserIdAndFollowingId(Long id, Long userId);
}

package org.springeel.oneclickrecipe.domain.follow.repository;

import java.util.Optional;
import org.springeel.oneclickrecipe.domain.follow.entity.Follow;
import org.springeel.oneclickrecipe.domain.follow.entity.FollowId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, FollowId> {

    Optional<Follow> findByUserIdAndFollowingId(Long userId, Long followingId);

    boolean existsByUserIdAndFollowingId(Long userId, Long followingId);

    long countByFollowingId(Long followingId);
}

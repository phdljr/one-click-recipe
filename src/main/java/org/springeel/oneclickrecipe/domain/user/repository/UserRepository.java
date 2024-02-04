package org.springeel.oneclickrecipe.domain.user.repository;

import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springeel.oneclickrecipe.domain.user.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByRole(UserRole role);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

}

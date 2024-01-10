package org.springeel.oneclickrecipe.domain.user.repository;

import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long userId);
}

package org.springeel.oneclickrecipe.domain.user;

import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}

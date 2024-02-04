package org.springeel.oneclickrecipe.domain.userfood.repository;

import java.util.Optional;
import org.springeel.oneclickrecipe.domain.userfood.entity.UserFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFoodRepository extends JpaRepository<UserFood, Long> {

    Optional<UserFood> findByName(String name);

    Optional<UserFood> findByIdAndName(Long id, String name);
}

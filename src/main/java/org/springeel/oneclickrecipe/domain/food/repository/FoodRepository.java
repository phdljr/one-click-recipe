package org.springeel.oneclickrecipe.domain.food.repository;

import java.util.Optional;
import org.springeel.oneclickrecipe.domain.food.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {

    Optional<Food> findByName(String name);
}

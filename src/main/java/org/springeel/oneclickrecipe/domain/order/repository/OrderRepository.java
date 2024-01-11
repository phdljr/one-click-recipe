package org.springeel.oneclickrecipe.domain.order.repository;

import java.util.List;
import java.util.Optional;
import org.springeel.oneclickrecipe.domain.order.entity.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // 사용자 ID에 따라 주문 목록 찾는 기능
    List<Order> findAllByUserId(Long userId);

    List<Order> findByUserId(Long userId);

    @EntityGraph
    Optional<Order> findByIdAndUserIdWithDetails(Long orderId, Long userId);
}
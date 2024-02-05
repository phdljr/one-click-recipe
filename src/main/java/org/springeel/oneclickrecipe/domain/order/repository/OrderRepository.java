package org.springeel.oneclickrecipe.domain.order.repository;

import java.util.List;
import java.util.Optional;
import org.springeel.oneclickrecipe.domain.order.entity.Order;
import org.springeel.oneclickrecipe.domain.order.entity.OrderStatus;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = {"orderDetails"})
    Optional<Order> findByIdAndUser(Long orderId, User user);

    List<Order> findAllByUser(User user);

    boolean existsByUserAndStatusEquals(User user, OrderStatus orderStatus);
}
package org.springeel.oneclickrecipe.domain.order.repository;

import org.springeel.oneclickrecipe.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}

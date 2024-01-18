package org.springeel.oneclickrecipe.domain.orderdetail.repository;

import java.util.List;
import org.springeel.oneclickrecipe.domain.orderdetail.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    List<OrderDetail> findByOrderId(Long orderId);
}

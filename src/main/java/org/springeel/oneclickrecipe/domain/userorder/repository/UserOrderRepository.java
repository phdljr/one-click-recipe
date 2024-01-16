package org.springeel.oneclickrecipe.domain.userorder.repository;

import org.springeel.oneclickrecipe.domain.userorder.entity.UserOrder;
import org.springeel.oneclickrecipe.domain.userorder.entity.UserOrderId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOrderRepository extends JpaRepository<UserOrder, UserOrderId> {

}

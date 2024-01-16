package org.springeel.oneclickrecipe.domain.userorder.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springeel.oneclickrecipe.domain.order.entity.Order;
import org.springeel.oneclickrecipe.domain.user.entity.User;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TB_USER_ORDER")
@Entity
@IdClass(UserOrderId.class)
public class UserOrder {

    @Id
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
}

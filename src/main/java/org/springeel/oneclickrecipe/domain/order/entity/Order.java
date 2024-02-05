package org.springeel.oneclickrecipe.domain.order.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springeel.oneclickrecipe.domain.orderdetail.entity.OrderDetail;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springeel.oneclickrecipe.global.entity.BaseEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TB_ORDER")
@Entity
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(nullable = false)
    private String receiverName;

    @Column(nullable = false)
    private String receiverPhoneNumber;

    @Column(nullable = false)
    private String senderName;

    @Column(nullable = false)
    private String senderPhoneNumber;

    @Column(nullable = false)
    private String address;

    @Column
    private String addressDetail;

    @Column
    private String requirement;

    @Column(nullable = false)
    private Integer totalPrice;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private User user;

    // OrderDetail 엔티티와 양방향 매핑
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private final List<OrderDetail> orderDetails = new ArrayList<>();

    @Builder
    public Order(
        final String receiverName,
        final String receiverPhoneNumber,
        final String senderName,
        final String senderPhoneNumber,
        final String address,
        final String addressDetail,
        final String requirement,
        final Integer totalPrice,
        final OrderStatus status,
        final User user
    ) {
        this.receiverName = receiverName;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.senderName = senderName;
        this.senderPhoneNumber = senderPhoneNumber;
        this.address = address;
        this.addressDetail = addressDetail;
        this.requirement = requirement;
        this.totalPrice = totalPrice;
        this.status = status;
        this.user = user;
    }

    public void updateStatus(final OrderStatus orderStatus) {
        this.status = orderStatus;
    }
}

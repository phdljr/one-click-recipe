package org.springeel.oneclickrecipe.domain.order.dto.service;

import java.util.List;
import org.springeel.oneclickrecipe.domain.order.entity.OrderStatus;
import org.springeel.oneclickrecipe.domain.orderdetail.entity.OrderDetail;

public record OrderReadResponseDto(
    Long orderId,
    String receiverName,
    String receiverPhoneNumber,
    String address,
    String addressDetail,
    Integer totalPrice,
    OrderStatus orderStatus,
    List<OrderDetail> orderDetails // 주문 상세 정보를 담을 리스트
) {

}
package org.springeel.oneclickrecipe.domain.order.dto.service;

import java.util.List;
import org.springeel.oneclickrecipe.domain.order.entity.OrderStatus;
import org.springeel.oneclickrecipe.domain.orderdetail.dto.OrderDetailDto;

public record OrderReadResponseDto(
    Long id,
    String receiverName,
    String receiverPhoneNumber,
    String address,
    String addressDetail,
    Integer totalPrice,
    OrderStatus orderStatus,
    List<OrderDetailDto> orderDetails // 주문 상세 정보를 담을 리스트
) {

}
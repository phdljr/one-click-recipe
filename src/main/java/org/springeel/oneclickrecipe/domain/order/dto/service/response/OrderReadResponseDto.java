package org.springeel.oneclickrecipe.domain.order.dto.service.response;

import java.util.List;
import lombok.Builder;
import org.springeel.oneclickrecipe.domain.order.entity.OrderStatus;
import org.springeel.oneclickrecipe.domain.orderdetail.dto.OrderDetailReadResponseDto;

@Builder
public record OrderReadResponseDto(
    Long id,
    String receiverName,
    String receiverPhoneNumber,
    String address,
    String addressDetail,
    Integer totalPrice,
    OrderStatus orderStatus,
    List<OrderDetailReadResponseDto> orderDetails // 주문 상세 정보를 담을 리스트
) {

}
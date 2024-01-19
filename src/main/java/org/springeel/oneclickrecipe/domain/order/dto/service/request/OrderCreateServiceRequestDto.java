package org.springeel.oneclickrecipe.domain.order.dto.service.request;

import lombok.Builder;

@Builder
public record OrderCreateServiceRequestDto(
    String receiverName,
    String receiverPhoneNumber,
    String senderName,
    String senderPhoneNumber,
    String address,
    String addressDetail,
    String requirement
) {

}

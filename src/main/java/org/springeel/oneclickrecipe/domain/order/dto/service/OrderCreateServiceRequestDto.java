package org.springeel.oneclickrecipe.domain.order.dto.service;

public record OrderCreateServiceRequestDto(
    String receiverName,
    String receiverPhoneNumber,
    String senderName,
    String senderPhoneNumber,
    String address,
    String addressDetail,
    String requirement,
    Integer totalPrice,
    Long userId
) {

}

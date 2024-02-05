package org.springeel.oneclickrecipe.domain.order.dto.controller;

public record OrderCreateControllerRequestDto(
    String receiverName,
    String receiverPhoneNumber,
    String senderName,
    String senderPhoneNumber,
    String address,
    String addressDetail,
    String requirement
) {

}

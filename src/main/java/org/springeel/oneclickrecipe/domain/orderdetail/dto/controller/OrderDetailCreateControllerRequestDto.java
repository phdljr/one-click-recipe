package org.springeel.oneclickrecipe.domain.orderdetail.dto.controller;

public record OrderDetailCreateControllerRequestDto(
    Long orderId,
    String foodName,
    Short amount,
    Integer price
) {

}

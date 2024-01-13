package org.springeel.oneclickrecipe.domain.orderdetail.dto.controller;

public record OrderDetailCreateControllerRequestDto(
    String foodName,
    Short amount,
    Integer price
) {

}

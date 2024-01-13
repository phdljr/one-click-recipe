package org.springeel.oneclickrecipe.domain.orderdetail.dto.service;

public record OrderDetailCreateResponseDto(
    Long id,
    String foodName,
    Short amount,
    Integer price
) {

}

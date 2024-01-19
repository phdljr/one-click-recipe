package org.springeel.oneclickrecipe.domain.orderdetail.dto;

public record OrderDetailReadResponseDto(
    Long id,
    String foodName,
    Short amount,
    Integer price
) {

}
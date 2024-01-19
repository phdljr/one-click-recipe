package org.springeel.oneclickrecipe.domain.orderdetail.dto;

public record OrderDetailReadResponseDto(
    Long id,
    String name,
    Short amount,
    Integer price
) {

}
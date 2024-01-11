package org.springeel.oneclickrecipe.domain.orderdetail.dto;

public record OrderDetailDto(
    Long id,
    String foodName,
    Short amount,
    Integer price
) {

}
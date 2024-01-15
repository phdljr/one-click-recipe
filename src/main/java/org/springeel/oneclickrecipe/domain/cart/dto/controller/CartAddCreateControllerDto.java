package org.springeel.oneclickrecipe.domain.cart.dto.controller;

// 장바구니에 상품을 추가할 때 요청하는 Dto
public record CartAddCreateControllerDto(
    Long userId,
    Long recipeFoodId
) {

}

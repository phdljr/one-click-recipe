package org.springeel.oneclickrecipe.domain.cart.dto.controller;

// 장바구니에 상품을 추가한 후 응답하는 Dto
public record CartCreateControllerResponseDto(
    Long userId,
    Long recipeFoodId
) {

}

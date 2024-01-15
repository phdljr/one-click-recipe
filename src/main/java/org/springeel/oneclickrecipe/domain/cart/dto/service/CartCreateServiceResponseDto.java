package org.springeel.oneclickrecipe.domain.cart.dto.service;

// 장바구니에 상품을 추가한 후 결과를 담는 Dto
public record CartCreateServiceResponseDto(
    Long userId,
    Long recipeFoodId
) {

}

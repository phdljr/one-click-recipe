package org.springeel.oneclickrecipe.domain.cart.dto.service;

// 장바구니에 상품을 추가할 때 필요한 정보를 담는 Dto
public record CartCreateServiceRequestDto(
    Long userId,
    Long recipeFoodId
) {

}

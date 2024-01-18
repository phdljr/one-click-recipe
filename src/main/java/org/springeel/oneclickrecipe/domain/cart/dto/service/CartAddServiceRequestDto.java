package org.springeel.oneclickrecipe.domain.cart.dto.service;

import java.util.List;

public record CartAddServiceRequestDto(
    Long userId,
    List<Long> recipeFoodIds
) {

}
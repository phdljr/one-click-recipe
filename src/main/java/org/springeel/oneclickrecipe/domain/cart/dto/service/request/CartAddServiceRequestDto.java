package org.springeel.oneclickrecipe.domain.cart.dto.service.request;

import java.util.List;

public record CartAddServiceRequestDto(
    List<Long> recipeFoodIds
) {

}
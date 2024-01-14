package org.springeel.oneclickrecipe.domain.orderdetail.service;

import org.springeel.oneclickrecipe.domain.orderdetail.dto.controller.OrderDetailCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.orderdetail.dto.service.OrderDetailCreateResponseDto;

public interface OrderDetailService {

    OrderDetailCreateResponseDto createOrderDetail(
        OrderDetailCreateControllerRequestDto requestDto);
}

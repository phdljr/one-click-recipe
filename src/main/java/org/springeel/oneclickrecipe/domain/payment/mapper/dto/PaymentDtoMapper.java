package org.springeel.oneclickrecipe.domain.payment.mapper.dto;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import org.mapstruct.Mapper;
import org.springeel.oneclickrecipe.domain.payment.dto.controller.KakaoPayApprovalControllerRequestDto;
import org.springeel.oneclickrecipe.domain.payment.dto.service.KakaoPayApprovalServiceRequestDto;

@Mapper(componentModel = SPRING)
public interface PaymentDtoMapper {

    KakaoPayApprovalServiceRequestDto toKakaoPayApprovalServiceRequestDto(
        KakaoPayApprovalControllerRequestDto controllerRequestDto);
}

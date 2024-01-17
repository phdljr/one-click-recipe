package org.springeel.oneclickrecipe.domain.payment.controller;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.payment.dto.controller.KakaoPayApprovalControllerRequestDto;
import org.springeel.oneclickrecipe.domain.payment.dto.kakaopay.KakaoPayApprovalResponseDto;
import org.springeel.oneclickrecipe.domain.payment.dto.kakaopay.KakaoPayReadyResponseDto;
import org.springeel.oneclickrecipe.domain.payment.dto.service.KakaoPayApprovalServiceRequestDto;
import org.springeel.oneclickrecipe.domain.payment.mapper.dto.PaymentDtoMapper;
import org.springeel.oneclickrecipe.domain.payment.service.PaymentService;
import org.springeel.oneclickrecipe.global.security.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentDtoMapper paymentDtoMapper;

    @PostMapping("/orders/{orderId}/payments/kakaopay/ready")
    public ResponseEntity<KakaoPayReadyResponseDto> readyKakaoPay(
        @PathVariable Long orderId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        KakaoPayReadyResponseDto responseDto = paymentService
            .readyKakaoPay(orderId, userDetails.user());
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/orders/{orderId}/payments/kakaopay/approve")
    public ResponseEntity<KakaoPayApprovalResponseDto> approveKakaoPay(
        @PathVariable Long orderId,
        @RequestBody KakaoPayApprovalControllerRequestDto controllerRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        KakaoPayApprovalServiceRequestDto serviceRequestDto = paymentDtoMapper
            .toKakaoPayApprovalServiceRequestDto(controllerRequestDto);
        KakaoPayApprovalResponseDto responseDto = paymentService
            .approveKakaoPay(orderId, serviceRequestDto, userDetails.user());
        return ResponseEntity.ok(responseDto);
    }
}

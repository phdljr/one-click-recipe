package org.springeel.oneclickrecipe.domain.payment.service;

import org.springeel.oneclickrecipe.domain.payment.dto.kakaopay.KakaoPayApprovalResponseDto;
import org.springeel.oneclickrecipe.domain.payment.dto.kakaopay.KakaoPayReadyResponseDto;
import org.springeel.oneclickrecipe.domain.payment.dto.service.KakaoPayApprovalServiceRequestDto;
import org.springeel.oneclickrecipe.domain.user.entity.User;

public interface PaymentService {

    KakaoPayReadyResponseDto readyKakaoPay(Long serviceRequestDto, User user);

    KakaoPayApprovalResponseDto approveKakaoPay(final Long orderId,
        KakaoPayApprovalServiceRequestDto serviceRequestDto, User user);
}

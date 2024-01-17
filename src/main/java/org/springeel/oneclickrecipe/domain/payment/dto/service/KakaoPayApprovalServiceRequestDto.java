package org.springeel.oneclickrecipe.domain.payment.dto.service;

public record KakaoPayApprovalServiceRequestDto(
    String tid,
    String pg_token
) {

}

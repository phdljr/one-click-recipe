package org.springeel.oneclickrecipe.domain.payment.dto.controller;

public record KakaoPayApprovalControllerRequestDto(
    String tid,
    String pg_token
) {

}

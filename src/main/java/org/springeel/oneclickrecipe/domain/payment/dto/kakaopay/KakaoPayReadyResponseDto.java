package org.springeel.oneclickrecipe.domain.payment.dto.kakaopay;

public record KakaoPayReadyResponseDto(
    String tid,
    String next_redirect_pc_url,
    String created_at
) {

}

package org.springeel.oneclickrecipe.domain.payment.dto.kakaopay;

import java.time.LocalDateTime;

public record KakaoPayApprovalResponseDto(
    String aid,
    String tid,
    String cid,
    String partner_order_id,
    String partner_user_id,
    String payment_method_type,
    Amount amount,
    String item_name,
    String item_code,
    String quantity,
    LocalDateTime created_at,
    LocalDateTime approved_at
) {

}

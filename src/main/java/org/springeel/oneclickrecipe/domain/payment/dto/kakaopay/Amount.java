package org.springeel.oneclickrecipe.domain.payment.dto.kakaopay;

public record Amount(
    Integer total,
    Integer tax_free,
    Integer vat,
    Integer point,
    Integer discount,
    Integer green_deposit
) {

}

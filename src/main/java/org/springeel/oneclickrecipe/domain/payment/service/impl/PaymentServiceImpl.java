package org.springeel.oneclickrecipe.domain.payment.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springeel.oneclickrecipe.domain.order.entity.Order;
import org.springeel.oneclickrecipe.domain.order.entity.OrderStatus;
import org.springeel.oneclickrecipe.domain.order.exception.AlreadyProcessesOrderException;
import org.springeel.oneclickrecipe.domain.order.exception.NotFoundOrderException;
import org.springeel.oneclickrecipe.domain.order.exception.OrderErrorCode;
import org.springeel.oneclickrecipe.domain.order.repository.OrderRepository;
import org.springeel.oneclickrecipe.domain.payment.dto.kakaopay.KakaoPayApprovalResponseDto;
import org.springeel.oneclickrecipe.domain.payment.dto.kakaopay.KakaoPayReadyResponseDto;
import org.springeel.oneclickrecipe.domain.payment.dto.service.KakaoPayApprovalServiceRequestDto;
import org.springeel.oneclickrecipe.domain.payment.service.PaymentService;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

    private final RestTemplate restTemplate;
    private final OrderRepository orderRepository;

    @Value("${custom.front.host}")
    private String frontHost;

    @Value("${custom.kakao.admin-key}")
    private String adminKey;

    @Value("${custom.kakao.cid}")
    private String cid;

    @Override
    public KakaoPayReadyResponseDto readyKakaoPay(final Long orderId, final User user) {
        Order order = orderRepository.findByIdAndUser(orderId, user)
            .orElseThrow(() -> new NotFoundOrderException(OrderErrorCode.NOT_FOUND_ORDER));

        if (order.getStatus() != OrderStatus.WAITING) {
            throw new AlreadyProcessesOrderException(OrderErrorCode.ALREADY_PROCESS_ORDER);
        }

        HttpHeaders headers = getHttpHeaders();

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("partner_order_id", order.getId().toString());
        parameters.add("partner_user_id", user.getEmail());
        parameters.add("item_name", "딸깍! 레시피");
        parameters.add("quantity", "1");
        parameters.add("total_amount", order.getTotalPrice().toString());
        parameters.add("vat_amount", "0");
        parameters.add("tax_free_amount", "0");
        parameters.add("approval_url",
            frontHost + String.format("/orders/%d/payment/kakao/approve", order.getId()));
        parameters.add("cancel_url",
            frontHost + String.format("/orders/%d/payment/kakao/cancel", order.getId()));
        parameters.add("fail_url", frontHost + "/payment/kakao/fail");

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters,
            headers);

        KakaoPayReadyResponseDto responseDto = restTemplate.postForObject(
            "https://kapi.kakao.com/v1/payment/ready", requestEntity,
            KakaoPayReadyResponseDto.class);

        log.info("카카오페이 준비: {}", responseDto);
        return responseDto;
    }

    @Override
    public KakaoPayApprovalResponseDto approveKakaoPay(final Long orderId,
        final KakaoPayApprovalServiceRequestDto serviceRequestDto, final User user) {
        Order order = orderRepository.findByIdAndUser(orderId, user)
            .orElseThrow(() -> new NotFoundOrderException(OrderErrorCode.NOT_FOUND_ORDER));

        if (order.getStatus() != OrderStatus.WAITING) {
            throw new AlreadyProcessesOrderException(OrderErrorCode.ALREADY_PROCESS_ORDER);
        }

        HttpHeaders headers = getHttpHeaders();

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("tid", serviceRequestDto.tid());
        parameters.add("partner_order_id", orderId.toString());
        parameters.add("partner_user_id", user.getEmail());
        parameters.add("pg_token", serviceRequestDto.pg_token());

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters,
            headers);

        KakaoPayApprovalResponseDto responseDto = restTemplate.postForObject(
            "https://kapi.kakao.com/v1/payment/approve", requestEntity,
            KakaoPayApprovalResponseDto.class);

        order.updateStatus(OrderStatus.APPROVEMENT);
        orderRepository.save(order);

        log.info("카카오페이 승인: {}", responseDto);
        return responseDto;
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + adminKey);
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        return headers;
    }
}

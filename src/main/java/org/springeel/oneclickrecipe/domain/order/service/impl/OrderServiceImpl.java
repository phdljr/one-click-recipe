package org.springeel.oneclickrecipe.domain.order.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.cart.entity.Cart;
import org.springeel.oneclickrecipe.domain.cart.repository.CartRepository;
import org.springeel.oneclickrecipe.domain.order.dto.service.OrderCreateResponseDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.OrderCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.OrderReadAllResponseDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.OrderReadResponseDto;
import org.springeel.oneclickrecipe.domain.order.entity.Order;
import org.springeel.oneclickrecipe.domain.order.exception.NotFoundOrderException;
import org.springeel.oneclickrecipe.domain.order.exception.OrderErrorCode;
import org.springeel.oneclickrecipe.domain.order.mapper.entity.OrderEntityMapper;
import org.springeel.oneclickrecipe.domain.order.repository.OrderRepository;
import org.springeel.oneclickrecipe.domain.order.service.OrderService;
import org.springeel.oneclickrecipe.domain.orderdetail.entity.OrderDetail;
import org.springeel.oneclickrecipe.domain.orderdetail.mapper.OrderDetailMapper;
import org.springeel.oneclickrecipe.domain.orderdetail.repository.OrderDetailRepository;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderEntityMapper orderEntityMapper; // Mapper 추가
    private final OrderDetailMapper orderDetailMapper;

    // 주문 생성
    @Override
    public OrderCreateResponseDto createOrder(OrderCreateServiceRequestDto serviceRequestDto,
        User user) {

        // 장바구니 아이템 조회
        List<Cart> cartItems = cartRepository.findByUser(user);

        // totalPrice 계산
        int totalPrice = cartItems.stream()
            .mapToInt(
                cartItem -> (int) (cartItem.getRecipeFood().getFood().getPrice()
                    * cartItem.getRecipeFood()
                    .getAmount()))
            .sum();

        // Order 객체 생성
        Order order = orderEntityMapper.toEntity(serviceRequestDto, user,
            totalPrice); // Mapper를 이용한 객체 생성

        // OrderDetail 객체들 생성 및 Order에 연결
        List<OrderDetail> orderDetails = cartItems.stream()
            .map(cartItem -> orderDetailMapper.toOrderDetail(cartItem, order))
            .toList();

        order.getOrderDetails().addAll(orderDetails);

        // Order와 OrderDetail 정보 저장
        Order savedOrder = orderRepository.save(order);

        // 결과 반환
        return orderEntityMapper.toResponseDto(savedOrder);
    }

    // 주문 내역 목록 조회
    @Override
    public List<OrderReadAllResponseDto> getAllUserOrders(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);

        if (orders.isEmpty()) {
            throw new NotFoundOrderException(OrderErrorCode.NOT_FOUND_ORDER); // 주문 내역이 없는 경우 예외 처리
        }
        return orders.stream()
            .map(orderEntityMapper::toOrderReadAllResponseDto) // Order를 OrderReadAllResponseDto로 변환
            .toList();
    }

    // 주문 내역 단건 조회
    @Override
    public OrderReadResponseDto getOrderById(Long orderId, Long userId) {
        Order order = orderRepository.findByIdAndUserId(orderId, userId)
            .orElseThrow(() -> new NotFoundOrderException(OrderErrorCode.NOT_FOUND_ORDER));

        // 매퍼를 사용하여 Order 엔티티를 OrderReadResponseDto로 변환
        return orderEntityMapper.toOrderReadResponseDto(order);
    }
}
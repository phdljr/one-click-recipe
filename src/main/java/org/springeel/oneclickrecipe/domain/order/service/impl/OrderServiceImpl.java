package org.springeel.oneclickrecipe.domain.order.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.cart.entity.Cart;
import org.springeel.oneclickrecipe.domain.cart.exception.CartErrorCode;
import org.springeel.oneclickrecipe.domain.cart.exception.NotFoundCardItemException;
import org.springeel.oneclickrecipe.domain.cart.repository.CartRepository;
import org.springeel.oneclickrecipe.domain.order.dto.service.request.OrderCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.response.OrderCreateResponseDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.response.OrderReadAllResponseDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.response.OrderReadResponseDto;
import org.springeel.oneclickrecipe.domain.order.entity.Order;
import org.springeel.oneclickrecipe.domain.order.entity.OrderStatus;
import org.springeel.oneclickrecipe.domain.order.exception.AlreadyExistsWaitingOrderException;
import org.springeel.oneclickrecipe.domain.order.exception.ForbiddenDeleteOrderException;
import org.springeel.oneclickrecipe.domain.order.exception.NotFoundOrderException;
import org.springeel.oneclickrecipe.domain.order.exception.OrderErrorCode;
import org.springeel.oneclickrecipe.domain.order.mapper.entity.OrderEntityMapper;
import org.springeel.oneclickrecipe.domain.order.repository.OrderRepository;
import org.springeel.oneclickrecipe.domain.order.service.OrderService;
import org.springeel.oneclickrecipe.domain.orderdetail.entity.OrderDetail;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper;

    private final int PAGE_SIZE = 10;

    // 주문 생성
    @Override
    public OrderCreateResponseDto createOrder(OrderCreateServiceRequestDto serviceRequestDto,
        User user) {
        if (orderRepository.existsByUserAndStatusEquals(user, OrderStatus.WAITING)) {
            throw new AlreadyExistsWaitingOrderException(
                OrderErrorCode.ALREADY_EXIST_WAITING_ORDER);
        }

        List<Cart> cartItems = cartRepository.findAllByUser(user);
        if (cartItems.isEmpty()) {
            throw new NotFoundCardItemException(CartErrorCode.NOT_FOUND_CART_ITEM);
        }

        // totalPrice 계산
        int totalPrice = cartItems.stream()
            .mapToInt(cartItem ->
                cartItem.getRecipeFood().getFood().getPrice() *
                    cartItem.getRecipeFood().getAmount())
            .sum();

        Order order = orderEntityMapper.toOrder(serviceRequestDto, user,
            totalPrice);

        // OrderDetail 객체들 생성 및 Order에 연결
        List<OrderDetail> orderDetails = cartItems.stream()
//            .map(cartItem -> orderDetailEntityMapper.toOrderDetail(cartItem, order))
            .map(cartItem -> OrderDetail.builder()
                .order(order)
                .name(cartItem.getRecipeFood().getFood().getName())
                .amount(cartItem.getRecipeFood().getAmount())
                .unit(cartItem.getRecipeFood().getFood().getUnit())
                .price(cartItem.getRecipeFood().getAmount() * cartItem.getRecipeFood().getFood()
                    .getPrice())
                .build()
            )
            .toList();

        // Order와 OrderDetail 정보 저장
        order.getOrderDetails().addAll(orderDetails);
        Order savedOrder = orderRepository.save(order);

        return orderEntityMapper.toOrderCreateResponseDto(savedOrder);
    }

    // 주문 내역 목록 조회
    @Override
    public List<OrderReadAllResponseDto> getAllUserOrders(final Integer page, User user) {
        PageRequest pageRequest = PageRequest.of(page, PAGE_SIZE, Sort.by(Direction.DESC, "id"));
        Slice<Order> orders = orderRepository.findAllSliceByUser(user, pageRequest);
        return orders.stream()
            .map(orderEntityMapper::toOrderReadAllResponseDto)
            .toList();
    }

    @Override
    @Transactional
    public void deleteOrder(final Long orderId, final User user) {
        Order order = orderRepository.findByIdAndUser(orderId, user)
            .orElseThrow(() -> new NotFoundOrderException(OrderErrorCode.NOT_FOUND_ORDER));

        if (order.getStatus() != OrderStatus.WAITING) {
            throw new ForbiddenDeleteOrderException(OrderErrorCode.FORBIDDEN_DELETE_ORDER);
        }

        orderRepository.delete(order);
    }

    @Override
    public void checkWaitingOrder(final User user) {
        if (orderRepository.existsByUserAndStatusEquals(user, OrderStatus.WAITING)) {
            throw new AlreadyExistsWaitingOrderException(
                OrderErrorCode.ALREADY_EXIST_WAITING_ORDER);
        }
    }

    // 주문 내역 단건 조회
    @Override
    public OrderReadResponseDto getOrderById(Long orderId, User user) {
        Order order = orderRepository.findByIdAndUser(orderId, user)
            .orElseThrow(() -> new NotFoundOrderException(OrderErrorCode.NOT_FOUND_ORDER));
        return orderEntityMapper.toOrderReadResponseDto(order);
    }
}
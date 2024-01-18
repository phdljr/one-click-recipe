package org.springeel.oneclickrecipe.domain.order.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springeel.oneclickrecipe.domain.order.dto.service.OrderCreateResponseDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.OrderCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.OrderReadResponseDto;
import org.springeel.oneclickrecipe.domain.order.entity.Order;
import org.springeel.oneclickrecipe.domain.order.entity.OrderStatus;
import org.springeel.oneclickrecipe.domain.order.exception.NotFoundOrderException;
import org.springeel.oneclickrecipe.domain.order.repository.OrderRepository;
import org.springeel.oneclickrecipe.domain.order.service.OrderService;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springeel.oneclickrecipe.domain.user.entity.UserRole;
import org.springeel.oneclickrecipe.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    OrderService orderService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        User user = userRepository.save(User.builder()
            .email("test@test.test")
            .password("asdasdasdasdsadasweujiofyionusfhuaiosdfa")
            .nickname("testnickname")
            .role(UserRole.USER)
            .build());
        orderRepository.save(Order.builder()
            .user(user)
            .address("address")
            .addressDetail("addressDetail")
            .receiverName("receiverName")
            .receiverPhoneNumber("receiverPhoneNumber")
            .requirement("requirement")
            .senderName("senderName")
            .senderPhoneNumber("senderPhoneNumber")
            .status(OrderStatus.WAITING)
            .totalPrice(10000)
            .build());
    }

    @Test
    @DisplayName("주문을 생성한다.")
    public void createOrderTest() {
        // given
        User user = userRepository.findById(1L).get();
        OrderCreateServiceRequestDto requestDto =
            new OrderCreateServiceRequestDto("1", "2", "3", "4", "5", "6", "7");

        // when
        OrderCreateResponseDto responseDto = orderService.createOrder(requestDto, user);

        // then
        assertThat(responseDto.id()).isEqualTo(2L);
        assertThat(responseDto.orderStatus()).isEqualTo(OrderStatus.WAITING);
    }

    @Test
    @DisplayName("없는 주문을 조회해서 예외가 발생한다.")
    public void notFoundOrderTest() {
        // given
        User user = userRepository.findById(1L).get();

        // when - then
        assertThatThrownBy(() -> orderService.getOrderById(100L, user.getId()))
            .isInstanceOf(NotFoundOrderException.class);
    }

    @Test
    @DisplayName("주문을 조회한다.")
    public void getOrderTest() {
        // given
        User user = userRepository.findById(1L).get();

        // when
        OrderReadResponseDto responseDto = orderService.getOrderById(1L, user.getId());

        // then
        assertThat(responseDto).isNotNull();
    }
}
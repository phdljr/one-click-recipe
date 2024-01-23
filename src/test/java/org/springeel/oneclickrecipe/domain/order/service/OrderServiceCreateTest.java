package org.springeel.oneclickrecipe.domain.order.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springeel.oneclickrecipe.domain.cart.entity.Cart;
import org.springeel.oneclickrecipe.domain.cart.repository.CartRepository;
import org.springeel.oneclickrecipe.domain.food.entity.Food;
import org.springeel.oneclickrecipe.domain.food.entity.UnitType;
import org.springeel.oneclickrecipe.domain.order.dto.service.request.OrderCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.response.OrderCreateResponseDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.response.OrderReadAllResponseDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.response.OrderReadResponseDto;
import org.springeel.oneclickrecipe.domain.order.entity.Order;
import org.springeel.oneclickrecipe.domain.order.entity.OrderStatus;
import org.springeel.oneclickrecipe.domain.order.exception.NotFoundOrderException;
import org.springeel.oneclickrecipe.domain.order.mapper.entity.OrderEntityMapper;
import org.springeel.oneclickrecipe.domain.order.repository.OrderRepository;
import org.springeel.oneclickrecipe.domain.order.service.impl.OrderServiceImpl;
import org.springeel.oneclickrecipe.domain.orderdetail.entity.OrderDetail;
import org.springeel.oneclickrecipe.domain.orderdetail.mapper.entity.OrderDetailEntityMapper;
import org.springeel.oneclickrecipe.domain.recipefood.entity.RecipeFood;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springeel.oneclickrecipe.domain.user.entity.UserRole;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderServiceCreateTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderEntityMapper orderEntityMapper;

    @Mock
    private OrderDetailEntityMapper orderDetailEntityMapper;

    @InjectMocks
    private OrderServiceImpl orderService;

    private User testUser;
    private Food testFood;
    private RecipeFood testRecipeFood;
    private Cart testCart;
    private Order testOrder;
    private OrderDetail testOrderDetail;
    private OrderCreateServiceRequestDto testOrderCreateServiceRequestDto;
    private OrderReadAllResponseDto testOrderReadAllResponseDto;
    private OrderReadResponseDto testOrderReadResponseDto;
    private Long testOrderId;

    @BeforeEach
    public void setUp() {
        testUser = User.builder()
            .email("test@naver.com")
            .password("password")
            .nickname("testUser")
            .role(UserRole.USER)
            .build();

        testFood = Food.builder()
            .name("Test Food")
            .price(100)
            .unit(UnitType.COUNT)
            .build();

        testRecipeFood = RecipeFood.builder()
            .amount((short) 2)
            .recipe(null)
            .food(testFood)
            .build();

        testCart = Cart.builder()
            .user(testUser)
            .recipeFood(testRecipeFood)
            .build();

        testOrderCreateServiceRequestDto = OrderCreateServiceRequestDto.builder()
            .receiverName("배규태")
            .receiverPhoneNumber("010-1111-1111")
            .senderName("어니부기")
            .senderPhoneNumber("010-9999-9999")
            .address("여의도 수영장")
            .addressDetail("제 3 수영장")
            .requirement("어니부기에게 전해주세요")
            .build();

        testOrderReadResponseDto = OrderReadResponseDto.builder()
            .receiverName("배규태")
            .receiverPhoneNumber("010-1111-1111")
            .address("여의도 수영장")
            .addressDetail("제 3 수영장")
            .totalPrice(30000)
            .orderStatus(OrderStatus.WAITING)
            .build();

        testOrder = Order.builder()
            .receiverName("배규태")
            .receiverPhoneNumber("010-1111-1111")
            .senderName("어니부기")
            .senderPhoneNumber("010-9999-9999")
            .address("여의도 수영장")
            .addressDetail("제 3 수영장")
            .requirement("어니부기에게 전해주세요")
            .totalPrice(20000)
            .status(OrderStatus.WAITING)
            .user(testUser)
            .build();

        testOrderDetail = OrderDetail.builder()
            .name("Test Food")
            .amount((short) 2)
            .unit(UnitType.COUNT)
            .price(200)
            .order(testOrder)
            .build();

        testOrderId = 1L;

        when(cartRepository.findAllByUser(testUser)).thenReturn(Arrays.asList(testCart));
        when(orderEntityMapper.toOrder(any(OrderCreateServiceRequestDto.class), any(User.class),
            anyInt()))
            .thenReturn(testOrder);
        when(orderDetailEntityMapper.toOrderDetail(any(Cart.class), any(Order.class))).thenReturn(
            testOrderDetail);
        when(orderRepository.save(any(Order.class))).thenReturn(testOrder);
        when(orderEntityMapper.toOrderCreateResponseDto(any(Order.class)))
            .thenReturn(new OrderCreateResponseDto(1L, OrderStatus.WAITING));

        List<Order> orderList = Arrays.asList(testOrder);
        when(orderRepository.findAllByUser(testUser)).thenReturn(orderList);
        when(orderEntityMapper.toOrderReadAllResponseDto(testOrder))
            .thenReturn(testOrderReadAllResponseDto);

        when(orderRepository.findByIdAndUser(testOrderId, testUser))
            .thenReturn(Optional.of(testOrder));
        when(orderEntityMapper.toOrderReadResponseDto(testOrder))
            .thenReturn(testOrderReadResponseDto);
    }

    @Test
    @DisplayName("주문 생성 성공")
    void testCreateOrderSuccess() {
        // when
        OrderCreateResponseDto result = orderService.createOrder(testOrderCreateServiceRequestDto,
            testUser);

        // then
        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals(OrderStatus.WAITING, result.orderStatus());
    }

    @Test
    @DisplayName("주문 목록 조회 성공")
    void testReadAllOrderSuccess() {
        // when
        List<OrderReadAllResponseDto> result = orderService.getAllUserOrders(testUser);

        // then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("주문 단건 조회 성공")
    void testReadOrderSuccess() {
        // when
        OrderReadResponseDto result = orderService.getOrderById(testOrderId, testUser);

        // then
        assertNotNull(result);
    }

    @Test
    @DisplayName("주문 단건 조회 실패 - 주문 없음")
    public void testGetOrderByIdNotFound() {
        // given
        Long invalidOrderId = 999L;
        when(orderRepository.findByIdAndUser(invalidOrderId, testUser))
            .thenReturn(Optional.empty());

        // when & then
        assertThrows(NotFoundOrderException.class, () -> {
            orderService.getOrderById(invalidOrderId, testUser);
        });
    }


}
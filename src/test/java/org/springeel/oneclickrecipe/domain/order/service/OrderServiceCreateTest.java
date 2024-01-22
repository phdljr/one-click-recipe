package org.springeel.oneclickrecipe.domain.order.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Arrays;
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
import org.springeel.oneclickrecipe.domain.order.entity.Order;
import org.springeel.oneclickrecipe.domain.order.entity.OrderStatus;
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
public class OrderServiceCreateTest {

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
    private Cart testCart;
    private Food testFood;
    private RecipeFood testRecipeFood;
    private OrderCreateServiceRequestDto testOrderCreateServiceRequestDto;
    private Order testOrder;
    private OrderDetail testOrderDetail;

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

        when(cartRepository.findAllByUser(testUser)).thenReturn(Arrays.asList(testCart));
        when(orderEntityMapper.toOrder(any(OrderCreateServiceRequestDto.class), any(User.class),
            anyInt()))
            .thenReturn(testOrder);
        when(orderDetailEntityMapper.toOrderDetail(any(Cart.class), any(Order.class))).thenReturn(
            testOrderDetail);
        when(orderRepository.save(any(Order.class))).thenReturn(testOrder);
        when(orderEntityMapper.toOrderCreateResponseDto(any(Order.class)))
            .thenReturn(new OrderCreateResponseDto(1L, OrderStatus.WAITING));
    }

    @Test
    @DisplayName("주문 생성 성공")
    public void testCreateOrderSuccess() {
        // when
        OrderCreateResponseDto result = orderService.createOrder(testOrderCreateServiceRequestDto,
            testUser);

        // then
        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals(OrderStatus.WAITING, result.orderStatus());
    }
}

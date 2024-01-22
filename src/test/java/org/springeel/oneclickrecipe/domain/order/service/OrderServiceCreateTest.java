package org.springeel.oneclickrecipe.domain.order.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springeel.oneclickrecipe.domain.cart.entity.Cart;
import org.springeel.oneclickrecipe.domain.cart.repository.CartRepository;
import org.springeel.oneclickrecipe.domain.food.entity.Food;
import org.springeel.oneclickrecipe.domain.order.dto.service.request.OrderCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.order.entity.Order;
import org.springeel.oneclickrecipe.domain.order.mapper.entity.OrderEntityMapper;
import org.springeel.oneclickrecipe.domain.order.repository.OrderRepository;
import org.springeel.oneclickrecipe.domain.order.service.impl.OrderServiceImpl;
import org.springeel.oneclickrecipe.domain.orderdetail.entity.OrderDetail;
import org.springeel.oneclickrecipe.domain.orderdetail.mapper.entity.OrderDetailEntityMapper;
import org.springeel.oneclickrecipe.domain.recipefood.entity.RecipeFood;
import org.springeel.oneclickrecipe.domain.user.entity.User;
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
}

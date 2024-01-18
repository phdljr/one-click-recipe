package org.springeel.oneclickrecipe.domain.orderdetail.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springeel.oneclickrecipe.domain.cart.entity.Cart;
import org.springeel.oneclickrecipe.domain.order.entity.Order;
import org.springeel.oneclickrecipe.domain.orderdetail.entity.OrderDetail;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {

    @Mapping(source = "cart.recipeFood.food.name", target = "foodName")
    @Mapping(source = "cart.recipeFood.amount", target = "amount")
    @Mapping(source = "cart.recipeFood.food.price", target = "price")
    @Mapping(source = "order", target = "order")
    OrderDetail toOrderDetail(Cart cart, Order order);
}
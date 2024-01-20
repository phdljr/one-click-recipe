package org.springeel.oneclickrecipe.domain.orderdetail.mapper.entity;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springeel.oneclickrecipe.domain.cart.entity.Cart;
import org.springeel.oneclickrecipe.domain.order.entity.Order;
import org.springeel.oneclickrecipe.domain.orderdetail.entity.OrderDetail;

@Mapper(componentModel = SPRING)
public interface OrderDetailEntityMapper {

    @Mapping(source = "cart.recipeFood.food.unit", target = "unit")
    @Mapping(source = "cart.recipeFood.food.name", target = "name")
    @Mapping(source = "cart.recipeFood.amount", target = "amount")
    @Mapping(source = "cart.recipeFood.food.price", target = "price")
    @Mapping(source = "order", target = "order")
    OrderDetail toOrderDetail(Cart cart, Order order);
}
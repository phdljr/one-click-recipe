package org.springeel.oneclickrecipe.domain.orderdetail.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springeel.oneclickrecipe.domain.cart.entity.Cart;
import org.springeel.oneclickrecipe.domain.order.entity.Order;
import org.springeel.oneclickrecipe.domain.orderdetail.entity.OrderDetail;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {

    @Mapping(source = "recipeFood.food.name", target = "foodName")
    @Mapping(source = "recipeFood.amount", target = "amount")
    @Mapping(source = "recipeFood.food.price", target = "price")
    OrderDetail toOrderDetail(Cart cart, Order order);
}
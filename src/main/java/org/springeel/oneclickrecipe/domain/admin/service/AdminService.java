package org.springeel.oneclickrecipe.domain.admin.service;

import java.util.List;
import org.springeel.oneclickrecipe.domain.order.dto.service.response.OrderReadAllResponseDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.response.OrderReadResponseDto;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springeel.oneclickrecipe.domain.user.entity.UserRole;

public interface AdminService {

    List<User> getAllUsers();

    void updateUserRole(Long userId, UserRole newRole);

    List<OrderReadAllResponseDto> getUserOrders(Long userId);

    OrderReadResponseDto getUserOrder(Long userId, Long orderId);

}

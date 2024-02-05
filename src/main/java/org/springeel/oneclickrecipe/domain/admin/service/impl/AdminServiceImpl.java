package org.springeel.oneclickrecipe.domain.admin.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.admin.service.AdminService;
import org.springeel.oneclickrecipe.domain.order.dto.service.response.OrderReadAllResponseDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.response.OrderReadResponseDto;
import org.springeel.oneclickrecipe.domain.order.exception.NotFoundOrderException;
import org.springeel.oneclickrecipe.domain.order.exception.OrderErrorCode;
import org.springeel.oneclickrecipe.domain.order.mapper.entity.OrderEntityMapper;
import org.springeel.oneclickrecipe.domain.order.repository.OrderRepository;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springeel.oneclickrecipe.domain.user.entity.UserRole;
import org.springeel.oneclickrecipe.domain.user.exception.NotFoundUserException;
import org.springeel.oneclickrecipe.domain.user.exception.UserErrorCode;
import org.springeel.oneclickrecipe.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void updateUserRole(Long userId, UserRole newRole) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundUserException(UserErrorCode.NOT_FOUND_USER));
        user.changeRole(newRole);
    }

    @Override
    public List<OrderReadAllResponseDto> getUserOrders(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundUserException(UserErrorCode.NOT_FOUND_USER));
        return orderRepository.findAllByUser(user)
            .stream()
            .map(orderEntityMapper::toOrderReadAllResponseDto)
            .toList();
    }

    @Override
    public OrderReadResponseDto getUserOrder(Long userId, Long orderId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundUserException(UserErrorCode.NOT_FOUND_USER));
        return orderRepository.findByIdAndUser(orderId, user)
            .map(orderEntityMapper::toOrderReadResponseDto)
            .orElseThrow(() -> new NotFoundOrderException(OrderErrorCode.NOT_FOUND_ORDER));
    }

}

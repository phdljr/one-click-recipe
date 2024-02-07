package org.springeel.oneclickrecipe.domain.admin.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.admin.dto.UserRoleUpdateRequestDto;
import org.springeel.oneclickrecipe.domain.admin.service.AdminService;
import org.springeel.oneclickrecipe.domain.order.dto.service.response.OrderReadAllResponseDto;
import org.springeel.oneclickrecipe.domain.order.dto.service.response.OrderReadResponseDto;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
@RestController
public class AdminController {

    private final AdminService adminService;

    // 유저 목록 조회
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    // 유저 롤 변경
    @PatchMapping("/users/{userId}/role")
    public ResponseEntity<Void> updateUserRole(@PathVariable(name = "userId") Long userId,
        @RequestBody UserRoleUpdateRequestDto updateRequestDto) {
        adminService.updateUserRole(userId, updateRequestDto.role());
        return ResponseEntity.ok().build();
    }

    // 유저의 주문 목록 조회
    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<List<OrderReadAllResponseDto>> getUserOrders(
        @PathVariable(name = "userId") Long userId) {
        return ResponseEntity.ok(adminService.getUserOrders(userId));
    }

    // 유저의 주문 단건 조회
    @GetMapping("/users/{userId}/orders/{orderId}")
    public ResponseEntity<OrderReadResponseDto> getUserOrder(
        @PathVariable(name = "userId") Long userId,
        @PathVariable(name = "orderId") Long orderId) {
        return ResponseEntity.ok(adminService.getUserOrder(userId, orderId));
    }

}

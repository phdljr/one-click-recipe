package org.springeel.oneclickrecipe.domain.userfood.controller;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.userfood.dto.controller.UserFoodCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.userfood.dto.controller.UserFoodUpdateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.userfood.dto.service.request.UserFoodCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.userfood.dto.service.request.UserFoodUpdateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.userfood.mapper.dto.UserFoodDtoMapper;
import org.springeel.oneclickrecipe.domain.userfood.service.UserFoodService;
import org.springeel.oneclickrecipe.global.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class UserFoodController {

    private final UserFoodService userFoodService;
    private final UserFoodDtoMapper dtoMapper;

    @PostMapping("/user_foods/create-food")
    public ResponseEntity<?> createFood(
        @RequestBody UserFoodCreateControllerRequestDto controllerRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        UserFoodCreateServiceRequestDto serviceRequestDto =
            dtoMapper.toFoodCreateServiceDto(controllerRequestDto);
        userFoodService.createFood(serviceRequestDto, userDetails.user());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/admin/user_foods/{foodId}")
    public ResponseEntity<?> deleteFood(
        @PathVariable Long foodId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        userFoodService.deleteFood(foodId, userDetails.user());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/admin/user_foods/{foodId}")
    public ResponseEntity<?> updateFood(
        @PathVariable Long foodId,
        @RequestBody UserFoodUpdateControllerRequestDto controllerRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        UserFoodUpdateServiceRequestDto serviceRequestDto =
            dtoMapper.toFoodUpdateServiceDto(controllerRequestDto);
        userFoodService.updateFood(foodId, serviceRequestDto, userDetails.user());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/user_foods")
    public ResponseEntity<?> readAllFoods() {
        return ResponseEntity.status(HttpStatus.OK)
            .body(userFoodService.readAllFoods());
    }
}

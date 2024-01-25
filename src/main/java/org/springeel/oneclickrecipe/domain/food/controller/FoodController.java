package org.springeel.oneclickrecipe.domain.food.controller;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.food.dto.controller.FoodCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.food.dto.controller.FoodUpdateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.food.dto.service.request.FoodCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.food.dto.service.request.FoodUpdateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.food.mapper.dto.FoodDtoMapper;
import org.springeel.oneclickrecipe.domain.food.service.FoodService;
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
public class FoodController {

    private final FoodService foodService;
    private final FoodDtoMapper dtoMapper;

    @PostMapping("/admin/foods/create-food")
    public ResponseEntity<?> createFood(
        @RequestBody FoodCreateControllerRequestDto controllerRequestDto
    ) {
        FoodCreateServiceRequestDto serviceRequestDto =
            dtoMapper.toFoodCreateServiceDto(controllerRequestDto);
        foodService.createFood(serviceRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/admin/foods/{foodId}")
    public ResponseEntity<?> deleteFood(
        @PathVariable Long foodId
    ) {
        foodService.deleteFood(foodId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/admin/foods/{foodId}")
    public ResponseEntity<?> updateFood(
        @PathVariable Long foodId,
        @RequestBody FoodUpdateControllerRequestDto controllerRequestDto
    ) {
        FoodUpdateServiceRequestDto serviceRequestDto =
            dtoMapper.toFoodUpdateServiceDto(controllerRequestDto);
        foodService.updateFood(foodId, serviceRequestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/foods")
    public ResponseEntity<?> readAllFoods() {
        return ResponseEntity.status(HttpStatus.OK)
            .body(foodService.readAllFoods());
    }
}

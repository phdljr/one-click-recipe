package org.springeel.oneclickrecipe.domain.food.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TB_FOOD")
@Entity
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UnitType unit;

    @Builder
    public Food(
        final String name,
        final Integer price,
        final UnitType unit
    ) {
        this.name = name;
        this.price = price;
        this.unit = unit;
    }

    public void updateFood(String name, Integer price, UnitType unit) {
        this.name = name;
        this.price = price;
        this.unit = unit;
    }
}

package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class FoodProduct extends Product implements Perishable, Shippable {


    private LocalDate expirationDate;
    BigDecimal weight;

    public FoodProduct(UUID id, String name, Category category, BigDecimal price, LocalDate expirationDate, BigDecimal weight) {
        super(id, name, category, price);
        this.expirationDate = expirationDate;
        this.weight = weight;

        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price can't be negative");
        }
        if (weight.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Weight can't be negative");
        }

    }

    //productDetails() should look like: "Food: Milk, Expires: 2025-12-24".
    //todo: I guess this is my gettermethod from product.
    @Override
    String productDetails() {
        return "Food: " + name() + ", Expires: " + expirationDate;
    }

    //Shipping rule: cost = weight * 50.
    @Override
    public BigDecimal calculateShippingCost () {
       return weight.multiply(BigDecimal.valueOf(50));

    }

}


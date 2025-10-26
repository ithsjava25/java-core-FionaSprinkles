package com.example;

import java.math.BigDecimal;
import java.util.UUID;

public class ElectronicsProduct extends Product implements Shippable {

    private final int warrantymonths;
    // In kilograms
    private final BigDecimal weight;

    public ElectronicsProduct(UUID id, String name, Category category, BigDecimal price, int warrantymonths, BigDecimal weight) {
        super(id, name, category, price);
        this.warrantymonths = warrantymonths;
        this.weight = weight;

        if (warrantymonths < 0)
            throw new IllegalArgumentException("Warranty months cannot be negative.");
    }

    //productDetails() should look like: "Electronics: Laptop, Warranty: 24 months".
    @Override
    String productDetails() {
        return "Electronics: "+ name() + ", Warranty: "+ warrantymonths + " months" ;
    }

    //Shipping rule: base 79, add 49 if weight > 5.0 kg.
    //BigDecimal is an object
    @Override
    public BigDecimal calculateShippingCost () {
        BigDecimal shippingCost = new BigDecimal("79.0");
        BigDecimal extraCost = new BigDecimal("49.0");
        BigDecimal weightLimit = new BigDecimal("5.0");

        //compareto returns 0 if the weight is the SAME as weightlimit.
        if (weight.compareTo(weightLimit) > 0) {
            shippingCost = shippingCost.add(extraCost);
        }
        return shippingCost;
        }

    @Override
    public double weight() {
        return weight.doubleValue();
    }
}

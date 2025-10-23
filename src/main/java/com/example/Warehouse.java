package com.example;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {

    //store created instances, same name = same warehouse
    private static final Map<String, Warehouse> warehouses = new HashMap<>();
    private final String name;

    private Warehouse(String name) {
        this.name = name;
    }
    // validate
    public static Warehouse getInstance(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Warehouse name cannot be null or blank");
        }
        return warehouses.computeIfAbsent(name, key -> new Warehouse(key));
    }


}

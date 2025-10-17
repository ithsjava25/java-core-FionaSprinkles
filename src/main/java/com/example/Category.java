package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Category {

    private static final Map<String, Category> categories = new HashMap<>();
    private final String name;


    private Category(String name) {
        this.name = name;
    }

    public static Category of(String name) {
        //Validate
        if (name == null) {
            throw new IllegalArgumentException("Category name can't be null");
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException("Category name can't be blank");
        }
        //todo check this method.
        //Format name to = first letter uppercase and the rest to lowercase.
        String formatName = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();



        //if a category has the same normalized name as previous = return. Otherwise = create new hashMap key-value pair in categories
        return categories.computeIfAbsent(formatName, key -> new Category(key));
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Category category)) return false;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}


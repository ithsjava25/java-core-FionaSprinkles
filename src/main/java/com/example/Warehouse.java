package com.example;

import java.util.*;

public class Warehouse {

    //store created instances, same name = same warehouse
    private static final Map<String, Warehouse> warehouses = new HashMap<>();
    private final String name;
    private final Set<Product> products;

    private Warehouse(String name, Set<Product> products) {
        this.name = name;
        this.products = products;
    }
    // validate
    public static Warehouse getInstance(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Warehouse name cannot be null or blank");
        }
        //Create new warehouse and hashset
        return warehouses.computeIfAbsent(name, key -> new Warehouse(key,new HashSet<>()));
    }


    /*TODO Gör klart warehouse enl. instruktioner
    Grejer som är kvar:

     getProductById(UUID): return Optional.
    - updateProductPrice(UUID, BigDecimal): when not found, throw NoSuchElementException("Product not found with id:"). Also track changed products in getChangedProducts().
    - expiredProducts(): return List that are expired.
    - shippableProducts(): return List from stored products.
    - remove(UUID): remove the matching product if present.
     */

    //Validate and add product
    public void addProduct(Product product) {
        if  (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        products.add(product);
    }
    //getProducts(): return an unmodifiable copy.
    public List<Product> getProducts() {
        return Collections.unmodifiableList(new ArrayList<>(products));
    }
}

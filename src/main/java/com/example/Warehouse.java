package com.example;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Warehouse {

    //store created instances, same name = same warehouse
    private static final Map<String, Warehouse> warehouses = new HashMap<>();
    private final String name;
    private final Set<Product> products;
    private final Set<Product> changedProducts = new HashSet<>();

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
    // Search trough warehouse products and return first product id that matches value
    public Optional <Product> getProductById(UUID id) {
        return products.stream()
                .filter(p ->p.uuid().equals(id) )
                .findFirst();
    }

    //Find product via UUID. change price and add to list
    public void updateProductPrice(UUID id, BigDecimal newPrice) {
        Product product = products.stream()
                .filter(p -> p.uuid().equals(id))
                .findFirst().orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));

        product.price(newPrice);
        changedProducts.add(product);
    }
    //Also track changed products in getChangedProducts().
    //Can't find this in BasicTest??
    public List<Product> getChangedProducts(){
        return Collections.unmodifiableList(new ArrayList<>(changedProducts));
    }

    //Returns all expired products
    public List<Perishable> expiredProducts() {
        return products.stream()
                .filter(product -> product instanceof Perishable)
                .map(product -> (Perishable)product)
                .filter(Perishable::isExpired)
                .toList();
    }

    //Create and return List with products that implements shippable
    public List<Shippable> shippableProducts() {
        return products.stream()
                        .filter(product -> product instanceof Shippable)
                        .map(product -> (Shippable) product)
                        .toList();
    }

    public void remove (UUID id){
        products.removeIf(product -> product.uuid().equals(id));
    }

    public Map<Category, List<Product>> getProductsGroupedByCategories() {
        return getProducts().stream()
                .collect(Collectors.groupingBy(Product::category));
    }
    public void clearProducts(){
        products.clear();
    }
    public boolean isEmpty() {
        return products.isEmpty();
    }
}

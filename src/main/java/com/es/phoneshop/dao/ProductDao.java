package com.es.phoneshop.dao;

import com.es.phoneshop.model.product.Product;

import java.util.List;

public interface ProductDao {
    Product getProduct(Long id);
    List<Product> findProducts();
    List<Product> findProductsByDescription(String description);
    void save(Product product);
    void delete(Long id);
    List<Product> sortByDescription();
    List<Product> sortByPrice();
    List<Product> sortByDescriptionReversed();
    List<Product> sortByPriceReversed();

}

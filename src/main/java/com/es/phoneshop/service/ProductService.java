package com.es.phoneshop.service;

import com.es.phoneshop.model.product.Product;

import java.util.List;

public interface ProductService {
    List<Product> findProducts();
    List<Product> findProductsByDescription(String description);
    List<Product> sortByDescription();
    List<Product> sortByPrice();
    List<Product> sortByDescriptionReversed();
    List<Product> sortByPriceReversed();
    String getProductDetailsByCode(String code);
}

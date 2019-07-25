package com.es.phoneshop.service;

import com.es.phoneshop.dao.ProductDao;
import com.es.phoneshop.model.product.Product;

import java.util.List;

public interface ProductService {
    List<Product> findProducts(String query, String sort);

}

package com.es.phoneshop.service;

import com.es.phoneshop.dao.ArrayListProductDao;
import com.es.phoneshop.dao.ProductDao;
import com.es.phoneshop.model.product.Product;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayListProductService implements ProductService {
    private ProductDao productDao = ArrayListProductDao.getInstance();

    @Override
    public List<Product> findProducts() {
        return productDao.findProducts();
    }

    @Override
    public List<Product> findProductsByDescription(String description) {
        return productDao.findProductsByDescription(description);
    }

    @Override
    public List<Product> sortByDescription() {
        return productDao.findProducts().stream()
                .sorted(Comparator.comparing(Product::getDescription))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> sortByDescriptionReversed() {
        return productDao.findProducts().stream()
                .sorted(Comparator.comparing(Product::getDescription).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> sortByPrice() {
        return productDao.findProducts().stream()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> sortByPriceReversed() {
        return productDao.findProducts().stream()
                .sorted(Comparator.comparing(Product::getPrice))
                .collect(Collectors.toList());
    }

    @Override
    public String getProductDetailsByCode(String code) {
        if (productDao.getProductByCode(code) != null) {
            return "details about product with code " + code;
        } else {
            return null;
        }

    }
}

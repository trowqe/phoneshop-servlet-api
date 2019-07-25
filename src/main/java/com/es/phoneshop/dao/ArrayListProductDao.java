package com.es.phoneshop.dao;

import com.es.phoneshop.model.product.Product;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class ArrayListProductDao implements ProductDao {

    private static final ArrayListProductDao arrayListProductDaoInstance = new ArrayListProductDao();
    private List<Product> threadSaveArrayList = new CopyOnWriteArrayList<>();

    private ArrayListProductDao() {
    }

    public static ArrayListProductDao getInstance() {
        return arrayListProductDaoInstance;
    }

    @Override
    public Product findById(Long id) {
        return threadSaveArrayList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public Product getProductByCode(String code) {
        return threadSaveArrayList.stream()
                .filter(product -> product.getCode().equals(code))
                .findFirst()
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public List<Product> findAll() {
        return threadSaveArrayList;
    }


    @Override
    public void save(Product product) {
        if (product == null) {
            throw new IllegalArgumentException();
        }
        else if (product.getId() == null) {
            saveNewProduct(product);
        }
        else if (product.getId() != null) {
            updateProduct(product);
        }
    }

    // copyOnWriteArrList значит threadSaveArrayList.add(product) не над синхронизир
    private void saveNewProduct(Product product) {
        product.setId(generateProductId());
        threadSaveArrayList.add(product);
    }

    private Long generateProductId() {
        return new Random().nextLong();
    }

    private void updateProduct(Product product) {
        threadSaveArrayList = threadSaveArrayList.parallelStream()
                .filter(p -> !p.getId().equals(product.getId()))
                .collect(Collectors.toList());

        threadSaveArrayList.add(product);
    }

    // тут parallelStream() знач не над синхронизировать
    @Override
    public void delete(Long id) {
        threadSaveArrayList.parallelStream()
                .filter(product -> !product.getId().equals(id))
                .findFirst()
                .orElseThrow(ProductNotFoundException::new);
    }
}

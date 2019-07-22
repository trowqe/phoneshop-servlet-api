package com.es.phoneshop.dao;

import com.es.phoneshop.model.product.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

// doesn't check same id of products
public class ArrayListProductDao implements ProductDao {

    private static final ArrayListProductDao arrayListProductDaoInstance = new ArrayListProductDao();
    private List<Product> threadSaveArrayList;

    private ArrayListProductDao() {
        this.threadSaveArrayList = new CopyOnWriteArrayList<>();
    }

    public static ArrayListProductDao getInstance() {
        return arrayListProductDaoInstance;
    }

    @Override
    public Product getProduct(Long id) {
        return threadSaveArrayList.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public Product getProductByCode(String code) {
        return threadSaveArrayList.stream()
                .filter(product -> product.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

    @Override
    //The method returns products having non null price and stock level > 0
    public List<Product> findProducts() {
        return threadSaveArrayList.stream()
                .filter(product -> product.getStock() > 0)
                .filter(product -> product.getPrice() != null)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findProductsByDescription(String description) {
        String[] searchCriteria = description.split("\\s");
        Map<Product, Integer> productMap = new HashMap<>();
        for (Product product : findProducts()) {
            productMap.put(product, 0);
        }

        for (String criteria : searchCriteria) {
            productMap.forEach((key, value) -> {
                        if (key.getDescription().toLowerCase().contains(criteria.toLowerCase())) {
                            productMap.put(key, value + 1);
                        }
                    }
            );
        }
       return productMap.entrySet().stream()
                .filter(p -> p.getValue() > 0)
                .sorted(Map.Entry.<Product, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public void save(Product product) {
        threadSaveArrayList.add(product);
    }

    @Override
    public void delete(Long id) {
        threadSaveArrayList.removeIf(product -> product.getId().equals(id));
    }

}

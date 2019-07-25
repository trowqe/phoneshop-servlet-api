package com.es.phoneshop.service;

import com.es.phoneshop.dao.ArrayListProductDao;
import com.es.phoneshop.dao.ProductDao;
import com.es.phoneshop.model.product.Product;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao = ArrayListProductDao.getInstance();

    @Override
    public List<Product> findProducts(String query, String sort) {
        List<Product> result = productDao.findAll();

        if (query != null) {
            result = findProductsByQuery(query);
        }

        if (validateSort(sort) != null) {
            result = result.stream()
                    .sorted(validateSort(sort))
                    .collect(Collectors.toList());
        }

        return result;
    }



    ProductSortOptionEnum validateSort(String sort) {
        try {
            return ProductSortOptionEnum.valueOf(sort);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public List<Product> findProductsByQuery(String query) {
        String[] searchCriteria = query.split("\\s");
        List<Product> productList = productDao.findAll();

        Map<Product, Long> productSearchMatchMap = productList.stream()
                .collect(toMap(Function.identity(), p -> Arrays.stream(searchCriteria)
                        .filter(c -> p.getDescription().contains(c))
                        .count()));

        return productSearchMatchMap.entrySet().stream()
                .filter(v -> v.getValue() > 0)
                .sorted(Map.Entry.<Product, Long>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(toList());
    }

}


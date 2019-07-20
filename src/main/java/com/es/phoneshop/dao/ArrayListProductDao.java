package com.es.phoneshop.dao;

import com.es.phoneshop.model.product.Product;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

// doesn't check same id of products
public class ArrayListProductDao implements ProductDao {

    private List<Product> threadSaveArrayList;
    private static final ArrayListProductDao arrayListProductDaoInstance = new ArrayListProductDao();

    private ArrayListProductDao() {
        this.threadSaveArrayList = new CopyOnWriteArrayList<>();
        initArrayList(threadSaveArrayList);
    }

    public static ArrayListProductDao getInstance() {
        return arrayListProductDaoInstance;
    }

    private List initArrayList(List list) {
        Currency usd = Currency.getInstance("USD");
        list.add(new Product(1L, "sgs", "Samsung Galaxy S", new BigDecimal(100), usd, 100, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg"));
        list.add(new Product(2L, "sgs2", "Samsung Galaxy S II", new BigDecimal(200), usd, 0, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S%20II.jpg"));
        list.add(new Product(3L, "sgs3", "Samsung Galaxy S III", new BigDecimal(300), usd, 5, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S%20III.jpg"));
        list.add(new Product(4L, "iphone", "Apple iPhone", new BigDecimal(200), usd, 10, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Apple/Apple%20iPhone.jpg"));
        list.add(new Product(5L, "iphone6", "Apple iPhone 6", new BigDecimal(1000), usd, 30, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Apple/Apple%20iPhone%206.jpg"));
        list.add(new Product(6L, "htces4g", "HTC EVO Shift 4G", new BigDecimal(320), usd, 3, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/HTC/HTC%20EVO%20Shift%204G.jpg"));
        list.add(new Product(7L, "sec901", "Sony Ericsson C901", new BigDecimal(420), usd, 30, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Sony/Sony%20Ericsson%20C901.jpg"));
        list.add(new Product(8L, "xperiaxz", "Sony Xperia XZ", new BigDecimal(120), usd, 100, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Sony/Sony%20Xperia%20XZ.jpg"));
        list.add(new Product(9L, "nokia3310", "Nokia 3310", new BigDecimal(70), usd, 100, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Nokia/Nokia%203310.jpg"));
        list.add(new Product(10L, "palmp", "Palm Pixi", new BigDecimal(170), usd, 30, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Palm/Palm%20Pixi.jpg"));
        list.add(new Product(11L, "simc56", "Siemens C56", new BigDecimal(70), usd, 20, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Siemens/Siemens%20C56.jpg"));
        list.add(new Product(12L, "simc61", "Siemens C61", new BigDecimal(80), usd, 30, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Siemens/Siemens%20C61.jpg"));
        list.add(new Product(13L, "simsxg75", "Siemens SXG75", new BigDecimal(150), usd, 40, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Siemens/Siemens%20SXG75.jpg"));
        return list;
    }

    public List<Product> getThreadSaveArrayList() {
        return threadSaveArrayList;
    }

    @Override
    public Product getProduct(Long id) {
        Optional<Product> foundProduct =
                threadSaveArrayList.stream()
                        .filter(product -> product.getId() == id)
                        .findFirst();
        return foundProduct.get();
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
    public void save(Product product) {
        threadSaveArrayList.add(product);
    }

    @Override
    public void delete(Long id) {
        threadSaveArrayList.removeIf(product -> product.getId().equals(id));
    }

    @Override
    public List<Product> sortByDescription() {
        return threadSaveArrayList.stream()
                .sorted((s1, s2) -> {
                    String[] split1 = s1.getDescription().split(" ");
                    String[] split2 = s2.getDescription().split(" ");

                    int n = split2[0].compareTo(split1[0]);

                    if (n == 0) {
                        return split2[0].compareTo(
                                split1[0]);
                    }

                    return n;
                }).collect(Collectors.toList());
    }

    @Override
    public List<Product> sortByDescriptionReversed() {
        return threadSaveArrayList.stream()
                .sorted((s1, s2) -> {
                    String[] split1 = s1.getDescription().split(" ");
                    String[] split2 = s2.getDescription().split(" ");

                    int n = split1[0].compareTo(split2[0]);

                    if (n == 0) {
                        return split1[0].compareTo(
                                split2[0]);
                    }

                    return n;
                }).collect(Collectors.toList());
    }

    @Override
    public List<Product> sortByPrice() {
        return threadSaveArrayList.stream()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> sortByPriceReversed() {
        return threadSaveArrayList.stream()
                .sorted(Comparator.comparing(Product::getPrice))
                .collect(Collectors.toList());
    }
}

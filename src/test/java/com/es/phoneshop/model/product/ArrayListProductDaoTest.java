package com.es.phoneshop.model.product;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.Assert.*;

public class ArrayListProductDaoTest {
    private ProductDao productDao;

    @Before
    public void setup() {
        productDao = new ArrayListProductDao();
    }

    @Test
    public void deleteProductTest() {
        Product testProduct = new Product(23L, "simsxg75", "Siemens SXG75", new BigDecimal(150), Currency.getInstance("USD"), 40,
                "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Siemens/Siemens%20SXG75.jpg");
        productDao.delete(23L);
        assertEquals(productDao.getProduct(23L), testProduct);
    }

    @Test
    public void getProductTest() {
        Product testProduct = new Product(33L, "simsxg75", "Siemens SXG75", new BigDecimal(150), Currency.getInstance("USD"), 40,
                "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Siemens/Siemens%20SXG75.jpg");
        productDao.save(testProduct);
        assertEquals(productDao.getProduct(33L), testProduct);
    }

    @Test
    public void saveProductTest() {
        Product testProduct = new Product(43L, "simsxg75", "Siemens SXG75", new BigDecimal(150), Currency.getInstance("USD"), 40,
                "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Siemens/Siemens%20SXG75.jpg");
        productDao.save(testProduct);
        assertEquals(productDao.getProduct(43L), testProduct);
    }


    @Test
    public void findProductsTest() {
        Product testProductWithNullPrice = new Product(53L, "simsxg75", "Siemens SXG75", null, Currency.getInstance("USD"), 40,
                "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Siemens/Siemens%20SXG75.jpg");
        productDao.save(testProductWithNullPrice);
        assertFalse(productDao.findProducts().contains(testProductWithNullPrice));

        Product testProductWithStockLevel0 = new Product(63L, "simsxg75", "Siemens SXG75", null, Currency.getInstance("USD"), 0,
                "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Siemens/Siemens%20SXG75.jpg");
        productDao.save(testProductWithNullPrice);
        assertFalse(productDao.findProducts().contains(testProductWithStockLevel0));
    }
}

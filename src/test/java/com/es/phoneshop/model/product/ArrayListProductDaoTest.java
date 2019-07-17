package com.es.phoneshop.model.product;

import com.es.phoneshop.dao.ArrayListProductDao;
import com.es.phoneshop.dao.ProductDao;
import com.es.phoneshop.dao.ProductNotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.Assert.*;

public class ArrayListProductDaoTest {
    private ProductDao productDao;

    @Before
    public void setup() {
        productDao = ArrayListProductDao.getInstance();
    }

    @Test(expected = ProductNotFoundException.class)
    public void deleteProductTest() {
        Product testProduct = new Product(23L, "simsxg75", "Siemens SXG75", new BigDecimal(150), Currency.getInstance("USD"), 40,
                "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Siemens/Siemens%20SXG75.jpg");
        productDao.save(testProduct);
        productDao.delete(23L);
       productDao.getProduct(23L);
    }

    @Test(expected = ProductNotFoundException.class)
    public void getProductTest() {
        productDao.getProduct(-1L);
    }

    @Test
    public void getProductWithNonExistentIdTest() {
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
    public void findProductsWithoutNullPriceTest() {
        BigDecimal nullPrise = null;
        Product testProductWithNullPrice = new Product(53L, "simsxg75", "Siemens SXG75", nullPrise, Currency.getInstance("USD"), 40,
                "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Siemens/Siemens%20SXG75.jpg");
        productDao.save(testProductWithNullPrice);
        assertFalse(productDao.findProducts().contains(testProductWithNullPrice));
    }

    @Test
    public void findProductsWithoutStockLevel0Test() {
        Product testProductWithStockLevel0 = new Product(63L, "simsxg75", "Siemens SXG75", new BigDecimal(150), Currency.getInstance("USD"), 0,
                "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Siemens/Siemens%20SXG75.jpg");
        productDao.save(testProductWithStockLevel0);
        assertFalse(productDao.findProducts().contains(testProductWithStockLevel0));
    }
}

package com.es.phoneshop.service;

import com.es.phoneshop.model.product.Product;

import java.util.Comparator;

public enum ProductSortOptionEnum implements Comparator<Product> {
    priceAsc {
        @Override
        public int compare(Product p1, Product p2) {
            return p1.getPrice().compareTo(p2.getPrice());
        }
    },
    priceDesc {
        @Override
        public int compare(Product p1, Product p2) {
            return p2.getPrice().compareTo(p1.getPrice());
        }
    },
    nameAsc {
        @Override
        public int compare(Product p1, Product p2) {
            if (p1.getDescription() != null && p2.getDescription() != null) {
                return p1.getDescription().compareTo(p1.getDescription());
            } else if (p1.getDescription() != null) {
                return 1;
            } else {
                return -1;
            }
        }
    },
    nameDesc {
        @Override
        public int compare(Product p1, Product p2) {
            if (p2.getDescription() != null && p1.getDescription() != null) {
                return p2.getDescription().compareTo(p2.getDescription());
            } else if (p2.getDescription() != null) {
                return 1;
            } else {
                return -1;
            }
        }
    };

}

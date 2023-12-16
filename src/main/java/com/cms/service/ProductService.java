package com.cms.service;

import com.cms.model.Product;
import org.springframework.data.domain.Page;

public interface ProductService {
    Page<Product> getProducts(String title, Double minPrice, Double maxPrice, int page, int size);
}

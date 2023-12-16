package com.cms.service;

import com.cms.model.Product;
import com.cms.repository.ProductRepository;
import com.cms.repository.specification.ProductSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> getProducts(String title, Double minPrice, Double maxPrice, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Specification<Product> specification = buildSpecification(title, minPrice, maxPrice);
        return productRepository.findAll(specification, pageable);
    }

    private Specification<Product> buildSpecification(String title, Double minPrice, Double maxPrice) {
        Specification<Product> specification = Specification.where(null);

        if (title != null && !title.isEmpty()) {
            specification = specification.and(ProductSpecifications.titleContains(title));
        }

        if (minPrice != null && maxPrice != null) {
            specification = specification.and(ProductSpecifications.priceBetween(minPrice, maxPrice));
        }

        return specification;
    }
}

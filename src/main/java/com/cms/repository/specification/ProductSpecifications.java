package com.cms.repository.specification;

import com.cms.model.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {
    //TODO research how to auto generate meta-data models to avoid harcoding fields.Use E.g Product_.title instead "title"

    public static Specification<Product> titleContains(String keyword) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + keyword.toLowerCase() + "%");
    }

    public static Specification<Product> priceBetween(Double minPrice, Double maxPrice) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
    }

    public static Specification<Product> priceGreaterThanOrEqual(Double minPrice) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<Product> priceLessThanOrEqual(Double maxPrice) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

}

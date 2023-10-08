package com.prueba.products.service;

import com.prueba.products.dto.ProductDTO;

import java.util.List;
public interface SimilarProductsService {
    List<ProductDTO> getSimilarProducts(String productId);
}

package com.prueba.products.controller;

import com.prueba.products.dto.ProductDTO;
import com.prueba.products.service.SimilarProductsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
public class SimilarProductsController {

    @Autowired
    SimilarProductsService similarProductsService;

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    @GetMapping("/product/{productId}/similar")
    public Object getSimilarProducts(@PathVariable String productId) {
        log.debug("[SimilarProductsControllerImpl.getSimilarProducts] Calling with productId: {}", productId);
        List<ProductDTO> similarProductsList = similarProductsService.getSimilarProducts(productId);
        if(similarProductsList == null) {
            log.error("[SimilarProductsControllerImpl.getSimilarProducts] No similar products were found for productId: {}", productId);
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Product not Found");
        }
        return ResponseEntity.ok(similarProductsList);
    }
}
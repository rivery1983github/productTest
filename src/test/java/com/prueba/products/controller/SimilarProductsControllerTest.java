package com.prueba.products.controller;

import com.prueba.products.dto.ProductDTO;
import com.prueba.products.service.SimilarProductsService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.Mockito.when;


@SpringBootTest
public class SimilarProductsControllerTest {

    @InjectMocks
    private SimilarProductsController similarProductsController;

    @Mock
    private SimilarProductsService similarProductsService;

    @Test
    public void shouldGetResponse200WhenGetSimilarProducts() {

        ProductDTO product1 = new ProductDTO("22", "Pants", 39.99, false);
        ProductDTO product2 = new ProductDTO("200", "Trousers", 49.99, false);
        ProductDTO product3 = new ProductDTO("2000", "Jeans", 69.99, true);
        List<ProductDTO> products = List.of(product1, product2, product3);

        when(similarProductsService.getSimilarProducts("2")).thenReturn(products);
        ResponseEntity result = (ResponseEntity) similarProductsController.getSimilarProducts("2");
        assertEquals(result ,ResponseEntity.ok(products));
    }

    @Test
    public void shouldGetResponse404WhenGetSimilarProductsNotFountProducts() {

        ResponseEntity error = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not Found");

        when(similarProductsService.getSimilarProducts("1")).thenReturn(null);
        ResponseEntity result = (ResponseEntity) similarProductsController.getSimilarProducts("1");
        assertEquals(result ,error);
    }
}

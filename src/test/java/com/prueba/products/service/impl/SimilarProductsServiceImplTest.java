package com.prueba.products.service.impl;

import com.prueba.products.dto.ProductDTO;
import com.prueba.products.externalAPIServices.ExternalAPIServices;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SimilarProductsServiceImplTest {

    @InjectMocks
    private SimilarProductsServiceImpl similarProductsService;

    @Mock
    private ExternalAPIServices externalAPIServices;

    @Test
    public void shouldReturnNullWhenGetSimilarProductsAndProductIdIsNull(){
        List<ProductDTO> response = similarProductsService.getSimilarProducts(null);
        assertEquals(response, null);
    }

    @Test
    public void shouldReturnProductsWhenGetSimilarProductsAndProductIdI2(){
        String productId= "2";
        String[] similarProductsIds = {"22", "200", "2000"};
        ProductDTO product1 = new ProductDTO("22", "Pants", 39.99, false);
        ProductDTO product2 = new ProductDTO("200", "Trousers", 49.99, false);
        ProductDTO product3 = new ProductDTO("2000", "Jeans", 69.99, true);
        when(externalAPIServices.getSimilarProductsIds(productId)).thenReturn(similarProductsIds);
        when(externalAPIServices.getProductById(similarProductsIds[0])).thenReturn(product1);
        when(externalAPIServices.getProductById(similarProductsIds[1])).thenReturn(product2);
        when(externalAPIServices.getProductById(similarProductsIds[2])).thenReturn(product3);
        List<ProductDTO> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        List<ProductDTO> response = similarProductsService.getSimilarProducts(productId);
        assertEquals(response, products);
    }

    @Test
    public void shouldReturnNullWhenGetSimilarProductsAndProductIdI6(){
        String productId= "1";
        when(externalAPIServices.getSimilarProductsIds(productId)).thenReturn(null);
        List<ProductDTO> response = similarProductsService.getSimilarProducts(productId);
        assertEquals(response, null);
    }


}

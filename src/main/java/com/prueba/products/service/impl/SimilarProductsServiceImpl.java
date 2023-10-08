package com.prueba.products.service.impl;

import com.prueba.products.dto.ProductDTO;
import com.prueba.products.externalAPIServices.ExternalAPIServices;
import com.prueba.products.service.SimilarProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class SimilarProductsServiceImpl  implements SimilarProductsService{

    @Autowired
    private final ExternalAPIServices externalAPIServices;

    public List<ProductDTO> getSimilarProducts(String productId) {

        if(productId == null) return null;

        String[] similarProductsIds =  externalAPIServices.getSimilarProductsIds(productId);
        if( similarProductsIds == null) return null;

        List<ProductDTO> productList = new ArrayList<>();
        for (String id : similarProductsIds) {
           ProductDTO product = externalAPIServices.getProductById(id);
           if(product != null) {
               productList.add(product);
           }
        }

        return productList;
    }
}

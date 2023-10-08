package com.prueba.products.externalAPIServices;

import com.prueba.products.dto.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ExternalAPIServices {

    @Value("${external.api.url}")
    private String baseUrl;

    /**
     * Returns a list of similar Products to the given productId
     * @param productId
     * @return
     */
    public String[] getSimilarProductsIds(String productId) {
        RestTemplate restTemplate = new RestTemplate();
        try{
            String url = baseUrl.concat(productId).concat("/similarids");
            ResponseEntity<String[]> response =
                    restTemplate.getForEntity(url,
                            String[].class);
            return response.getBody();
        } catch (Exception e){
            log.warn("[ExternalAPIServices.getSimilarProductsIds] Not found any similar product with productId: {}", productId);
            return null;
        }
    }

    /**
     * Returns detail of a product
     * @param productId
     * @return
     */
    public ProductDTO getProductById(String productId) {
        RestTemplate restTemplate = new RestTemplate();
        try{
            String url = baseUrl.concat(productId);
            ResponseEntity<ProductDTO> response = restTemplate.getForEntity(url, ProductDTO.class);
            return response.getBody();
        } catch (Exception e){
            log.warn("[ExternalAPIServices.getProductById] Not found product detail of productId: {}", productId);
            return null;
        }
    }

}

package com.norestfortheapi.webshop.shitwishfrontend.service;

import com.norestfortheapi.webshop.shitwishfrontend.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProductServiceCaller {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${url.base}")
    private String baseUrl;

    @Value("${url.products}")
    private String productUrl;

    @Value("${url.users}")
    private String userUrl;

    @Value("${url.carts}")
    private String cartUrl;

    public List<Product> getProductList() {

        try {
            ResponseEntity<List<Product>> productList = restTemplate.exchange(baseUrl + productUrl, HttpMethod.GET,
                    null, new ParameterizedTypeReference<List<Product>>() {
                    });
            return productList.getBody();
        } catch (Exception e) {
            log.error(e.getMessage());
            List<Product> products = new ArrayList<>();
            Product firstCoffeeThingy =
                    Product.builder().id(1L).name("First").price(10.00).description("Awesome").imagePath("1").build();
            Product secondCoffeeThingy =
                    Product.builder().id(2L).name("Second").price(10.00).description("Awesome").imagePath("2").build();
            products.add(firstCoffeeThingy);
            products.add(secondCoffeeThingy);
            return products;
        }
    }

    public Product getProduct(long id) {
        try {
            ResponseEntity<Product> productResponseEntity = restTemplate.exchange(baseUrl + productUrl + "/" + id,
                    HttpMethod.GET
                    , null, new ParameterizedTypeReference<Product>() {
                    });
            return productResponseEntity.getBody();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}

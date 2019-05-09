package com.norestfortheapi.webshop.shitwishfrontend.service;

import com.norestfortheapi.webshop.shitwishfrontend.model.Cart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class CartServiceCaller {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${url.base}")
    private String baseUrl;

    @Value("${url.carts}")
    private String cartUrl;

    public Cart getCart(long id) {
        try {
            ResponseEntity<Cart> wishUserResponseEntity = restTemplate.exchange(baseUrl + cartUrl + "/" + id,
                    HttpMethod.GET
                    , null, new ParameterizedTypeReference<Cart>() {
                    });
            return wishUserResponseEntity.getBody();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}

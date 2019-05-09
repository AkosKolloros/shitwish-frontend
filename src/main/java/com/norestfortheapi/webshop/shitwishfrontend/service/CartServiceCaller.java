package com.norestfortheapi.webshop.shitwishfrontend.service;

import com.norestfortheapi.webshop.shitwishfrontend.model.Cart;
import com.norestfortheapi.webshop.shitwishfrontend.model.CartItem;
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

    @Value("${url.carts}")
    private String url;

    public Cart getCart(Long id) {
        try {
            ResponseEntity<Cart> wishUserResponseEntity = restTemplate.exchange(url + id,
                    HttpMethod.GET
                    , null, new ParameterizedTypeReference<Cart>() {
                    });
            return wishUserResponseEntity.getBody();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ExecutionFailedException(e.getMessage());
        }
    }

    public Cart addItemToCart(Long id, CartItem cartItem) {
        try {
            String postUrl = url + id + "/products";
            ResponseEntity<Cart> wishUserResponseEntity = restTemplate.postForEntity(postUrl, cartItem, Cart.class);
            return wishUserResponseEntity.getBody();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ExecutionFailedException(e.getMessage());
        }
    }

    public Cart createNewCart(Cart cart) {
        try {
            ResponseEntity<Cart> wishUserResponseEntity = restTemplate.postForEntity(url, cart, Cart.class);
            return wishUserResponseEntity.getBody();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ExecutionFailedException(e.getMessage());
        }
    }

    public Cart deleteItemFromCart(Long id, Long productId) {
        String deleteUrl = url + id + "/products/" + productId;
        try {
            ResponseEntity<Cart> wishUserResponseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.DELETE,
                    null,
                    Cart.class);
            return wishUserResponseEntity.getBody();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ExecutionFailedException(e.getMessage());
        }
    }

}


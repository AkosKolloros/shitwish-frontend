package com.norestfortheapi.webshop.shitwishfrontend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {
    private long id;
    private long userId;
    // productID, quantity
    private HashMap<Long, Integer> productIDs;
    // product, quantity
    private HashMap<Product, Integer> products;
    private CartStatus status;

    public int getSumProducts() {
        return 0;
    }
}

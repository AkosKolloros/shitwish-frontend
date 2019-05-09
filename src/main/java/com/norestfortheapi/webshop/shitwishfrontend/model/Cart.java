package com.norestfortheapi.webshop.shitwishfrontend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {
    private long id;
    private long userId;
    private List<CartItem> products;
    private CartStatus status;

    public int getSumProducts() {
        if (products != null) {
            return products.size();
        }
        return 0;
    }
}

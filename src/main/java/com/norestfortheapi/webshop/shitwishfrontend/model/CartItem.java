package com.norestfortheapi.webshop.shitwishfrontend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {

    private Long productId;

    private int quantity;

    private BigDecimal price;

    @JsonIgnoreProperties({"products"})
    private Cart cart;
}

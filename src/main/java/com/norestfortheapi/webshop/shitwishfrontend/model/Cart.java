package com.norestfortheapi.webshop.shitwishfrontend.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Cart {
    private Long id;
    private Long userId;
    private List<CartItem> products;
    private CartStatus status;
    private BigDecimal amountToPay;
    private Integer productNumber;

    @JsonGetter("productNumber")
    public Integer getSumProducts() {
        if (products != null) {
            return products.stream().mapToInt(CartItem::getQuantity).sum();
        }
        return 0;
    }

    @JsonGetter("amountToPay")
    public BigDecimal getTheAmountToPay() {
        if (products != null) {
            return products.stream()
                    .map(item -> item.getPrice().multiply(new BigDecimal(item.getQuantity())))
                    .reduce(new BigDecimal(0), BigDecimal::add);
        }
        return null;
    }
}

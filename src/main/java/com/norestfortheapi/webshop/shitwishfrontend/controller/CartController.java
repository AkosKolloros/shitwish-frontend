package com.norestfortheapi.webshop.shitwishfrontend.controller;

import com.norestfortheapi.webshop.shitwishfrontend.model.Cart;
import com.norestfortheapi.webshop.shitwishfrontend.model.CartItem;
import com.norestfortheapi.webshop.shitwishfrontend.model.CartStatus;
import com.norestfortheapi.webshop.shitwishfrontend.service.CartServiceCaller;
import com.norestfortheapi.webshop.shitwishfrontend.service.ExecutionFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/cart")
@SessionAttributes({"cart"})
public class CartController {

    @Autowired
    CartServiceCaller cartServiceCaller;

    @PostMapping("/add-item")
    @ResponseStatus(HttpStatus.CREATED)
    public Cart addToCart(@RequestBody CartItem cartItem, Model model, @ModelAttribute("cart") Cart cart){
        try {
            if (null == cart.getId()) {
                cart = cartServiceCaller.createNewCart(Cart.builder().status(CartStatus.NEW).build());
            }
            Cart updatedCart = cartServiceCaller.addItemToCart(cart.getId(), cartItem);
            model.addAttribute("cart", updatedCart);
            return updatedCart;
        } catch (ExecutionFailedException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
        }
    }

    @PostMapping("/delete-item")
    @ResponseStatus(HttpStatus.CREATED)
    public Cart deleteFromCart(@RequestBody CartItem cartItem, Model model){
        try {
            Cart cart = cartServiceCaller.deleteItemFromCart(cartItem.getCart().getId(), cartItem.getProductId());
            model.addAttribute("cart", cart);
            return cart;
        } catch (ExecutionFailedException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
        }
    }
}


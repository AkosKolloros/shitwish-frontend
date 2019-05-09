package com.norestfortheapi.webshop.shitwishfrontend.controller;

import com.norestfortheapi.webshop.shitwishfrontend.model.Cart;
import com.norestfortheapi.webshop.shitwishfrontend.model.CartItem;
import com.norestfortheapi.webshop.shitwishfrontend.service.CartServiceCaller;
import com.norestfortheapi.webshop.shitwishfrontend.service.ExecutionFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartServiceCaller cartServiceCaller;

    @PostMapping("addItem")
    @ResponseStatus(HttpStatus.CREATED)
    public Cart addToCart(@RequestBody CartItem cartItem, HttpSession session){
        try {
            Cart cart = cartServiceCaller.addItemToCart(cartItem.getCart().getId(), cartItem);
            session.setAttribute("cart", cart);
            return cart;
        } catch (ExecutionFailedException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
        }
    }

    @DeleteMapping("deleteItem")
    @ResponseStatus(HttpStatus.CREATED)
    public Cart deleteFromCart(@RequestBody CartItem cartItem, HttpSession session){
        try {
            Cart cart = cartServiceCaller.deleteItemFromCart(cartItem.getCart().getId(), cartItem.getProductId());
            session.setAttribute("cart", cart);
            return cart;
        } catch (ExecutionFailedException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
        }
    }
}


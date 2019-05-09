package com.norestfortheapi.webshop.shitwishfrontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {

    @PostMapping
    public void addToCart() {

    }
}

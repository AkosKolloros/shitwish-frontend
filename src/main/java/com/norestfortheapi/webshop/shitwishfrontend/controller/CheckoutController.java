package com.norestfortheapi.webshop.shitwishfrontend.controller;

import com.norestfortheapi.webshop.shitwishfrontend.model.Cart;
import com.norestfortheapi.webshop.shitwishfrontend.service.CartServiceCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/checkout")
@SessionAttributes({"cart", "user"})
public class CheckoutController {

    @Autowired
    CartServiceCaller cartServiceCaller;

    @GetMapping("/{id}")
    public String getCheckoutPage(@PathVariable("id") Long id, Model model) {
        Cart cart = cartServiceCaller.getCart(id);
        model.addAttribute("cart", cart);
        return "checkout/checkout";
    }

}

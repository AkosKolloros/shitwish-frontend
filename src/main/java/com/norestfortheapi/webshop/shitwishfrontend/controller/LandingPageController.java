package com.norestfortheapi.webshop.shitwishfrontend.controller;

import com.norestfortheapi.webshop.shitwishfrontend.model.Cart;
import com.norestfortheapi.webshop.shitwishfrontend.model.Product;
import com.norestfortheapi.webshop.shitwishfrontend.model.WishUser;
import com.norestfortheapi.webshop.shitwishfrontend.service.ProductServiceCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes({"cart", "user"})
public class LandingPageController {

    @ModelAttribute("user")
    public WishUser getUser() {
        return new WishUser();
    }

    @ModelAttribute("cart")
    public Cart getCart() {
        return new Cart();
    }

    @Autowired
    private ProductServiceCaller productServiceCaller;

    @GetMapping("/")
    public String displayPage(Model model, @ModelAttribute WishUser user, @ModelAttribute Cart cart) {
        List<Product> products = productServiceCaller.getProductList();
        model.addAttribute("products", products);
        model.addAttribute("cart", cart);
        return "index";
    }
}

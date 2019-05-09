package com.norestfortheapi.webshop.shitwishfrontend.controller;

import com.norestfortheapi.webshop.shitwishfrontend.model.WishUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/logout")
@SessionAttributes({"user"})
public class LogoutController {

    @GetMapping
    public String logout(@ModelAttribute("user") WishUser user, Model model) {
        model.addAttribute("user", new WishUser());
        return "redirect:/";
    }

}
package com.norestfortheapi.webshop.shitwishfrontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/logout")
@SessionAttributes({"user"})
public class LogoutController {

    @GetMapping
    public String logout(Model model) {
        model.addAttribute("user", null);
        return "redirect:/";
    }

}
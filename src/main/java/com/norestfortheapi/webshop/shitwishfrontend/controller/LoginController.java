package com.norestfortheapi.webshop.shitwishfrontend.controller;

import com.norestfortheapi.webshop.shitwishfrontend.model.WishUser;
import com.norestfortheapi.webshop.shitwishfrontend.service.UserServiceCaller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
@SessionAttributes({"user"})
@Slf4j
public class LoginController {

    @Autowired
    UserServiceCaller userServiceCaller;

    @ModelAttribute("user")
    public WishUser getUser() {
        return new WishUser();
    }

    @GetMapping
    public String loginForm() {
        return "session/login";
    }

    @PostMapping
    public String login(@RequestParam String userName, @RequestParam String password, Model model) {
        WishUser loginUser = WishUser.builder().userName(userName).password(password).build();
        WishUser sessionUser = userServiceCaller.authenticateUser(loginUser);
        if (sessionUser != null){
            log.info("Log in successful.");
            //TODO set as session attribute
            model.addAttribute("user", sessionUser);
            log.info(model.toString());
            return "redirect:/";
        }
        log.info("Log in NOT successful.");
        return "redirect:/login";
    }
}

package com.norestfortheapi.webshop.shitwishfrontend.controller;

import com.norestfortheapi.webshop.shitwishfrontend.model.WishUser;
import com.norestfortheapi.webshop.shitwishfrontend.service.UserServiceCaller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
@Slf4j
public class RegistrationController {

    @Autowired
    UserServiceCaller userServiceCaller;

    @GetMapping
    public String registerForm() {
        return "session/registration";
    }

    @PostMapping
    public String register(@RequestParam String username, @RequestParam String password, @RequestParam String email) {
        WishUser user = WishUser.builder().userName(username).password(password).email(email).build();
        log.info(user.toString());
        if (userServiceCaller.postUser(user)){
            return "redirect:/login";
        }
        return "redirect:/registration";

    }
}

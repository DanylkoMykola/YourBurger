package com.danylko.yourburger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String getLoginPage() {
        return "auth/login";
    }

    @PostMapping("/logout")
    public String logout() {
        return "index";
    }
}

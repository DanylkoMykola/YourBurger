package com.danylko.yourburger.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {

    @GetMapping("/shares")
    public String getShares() {
        return "shares";
    }

    @GetMapping("/find-us")
    public String getFindUs() {
        return "find-on-map";
    }

    @GetMapping("about-us")
    public String getAboutUs() {
        return "about-us";
    }
}

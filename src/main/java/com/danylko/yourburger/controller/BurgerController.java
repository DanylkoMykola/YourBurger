package com.danylko.yourburger.controller;

import com.danylko.yourburger.entities.Burger;
import com.danylko.yourburger.service.BurgerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping(name = "/burgers")
@Controller
public class BurgerController {

    private Logger log = LoggerFactory.getLogger(BurgerController.class);

    private BurgerService burgerService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        List<Burger> burgers = burgerService.findAll();
        uiModel.addAttribute("burgers", burgers);
        return "burgers/list";
    }


}

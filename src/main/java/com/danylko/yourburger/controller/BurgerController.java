package com.danylko.yourburger.controller;

import com.danylko.yourburger.entities.Burger;
import com.danylko.yourburger.service.BurgerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.List;


@Controller
public class BurgerController {

    private Logger logger = LoggerFactory.getLogger(BurgerController.class);

    @Autowired
    private BurgerService burgerService;

    @RequestMapping(value = {"/templates/burgers", "/burgers" }, method = RequestMethod.GET)
    public String list(Model uiModel) {
        logger.info("Start method list");
        List<Burger> burgers = burgerService.findAll();
        uiModel.addAttribute("burgers", burgers);
        logger.info("End method list");
        return "burgers";
    }

   /* @GetMapping(value = "/{id}")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        Burger burger = burgerService.findById(id);
        uiModel.addAttribute("burger", burger);
        return "show";
    }

    @GetMapping(name = "/edit/{id}")
    public String updateForm(@PathVariable Long id, Model uiModel) {
        uiModel.addAttribute("burger", burgerService.findById(id));
        return "update";
    }

    @GetMapping(name = "/new")
    public String createForm(Model uiModel) {
        Burger burger = new Burger();
        uiModel.addAttribute("burger", burger);
        return "update";
    }*/

    @RequestMapping(value = "/burgerform", method = RequestMethod.POST)
    public String saveBurger(@ModelAttribute Burger burger, Model model) {
        burgerService.save(burger);
        model.addAttribute("burger", burger);
        return "burgerform";
    }
    @RequestMapping(value = "/burgerform", method = RequestMethod.GET)
    public String createBurger(@ModelAttribute Burger burger, Model model) {
        model.addAttribute("burger", burger);
        return "burgerform";
    }
}

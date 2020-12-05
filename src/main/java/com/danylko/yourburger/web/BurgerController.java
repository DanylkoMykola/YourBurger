package com.danylko.yourburger.web;

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

@RequestMapping(name = "/burgers")
@Controller
public class BurgerController {

    private Logger log = LoggerFactory.getLogger(BurgerController.class);

    @Autowired
    private BurgerService burgerService;

    @GetMapping
    public String list(Model uiModel) {
        List<Burger> burgers = burgerService.findAll();
        uiModel.addAttribute("burgers", burgers);
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
    }

    @PostMapping
    public String saveBurger(@Valid Burger burger) {
        burgerService.save(burger);
        return "redirect:/burgers/" + burger.getBurId();
    }*/


}

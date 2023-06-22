package org.lessons.springlamiapizzeriacrud.controller;

import org.lessons.springlamiapizzeriacrud.model.Pizza;
import org.lessons.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/pizza")
public class PizzaController {

    @Autowired
    private PizzaRepository repository;

    @GetMapping
    public String index(Model model) {
        List<Pizza> pizza = repository.findAll();
        model.addAttribute("pizzaList", pizza);
        return "/pizza/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable Integer id) {
        Pizza showPizza = null;
        try {
            List<Pizza> pizza = repository.findAll();
            for(Pizza p : pizza) {
                if(p.getId() == id){
                    showPizza = p;
                }
            }
        }  catch(IndexOutOfBoundsException e) {
            return "redirect:/pizza";
        }
        model.addAttribute("pizza", showPizza);
        return "/pizza/show";
    }
}

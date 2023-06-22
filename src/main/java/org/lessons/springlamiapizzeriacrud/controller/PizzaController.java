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
import java.util.Optional;

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
        // recupero pizza con id corrispondente
        Optional<Pizza> pizzaId = repository.findById(id);
        // se presente la recupero e la passo come attributo col model
        if (pizzaId.isPresent()) {
            Pizza pizzaShow = pizzaId.get();
            model.addAttribute("pizza", pizzaShow);
            return "/pizza/show";
        // altrimenti redirect alla index
        } else {
            return "redirect:/pizza";
        }
    }
}

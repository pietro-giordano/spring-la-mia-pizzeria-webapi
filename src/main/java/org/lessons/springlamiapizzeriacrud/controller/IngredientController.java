package org.lessons.springlamiapizzeriacrud.controller;

import jakarta.validation.Valid;
import org.lessons.springlamiapizzeriacrud.model.Ingredient;
import org.lessons.springlamiapizzeriacrud.model.Pizza;
import org.lessons.springlamiapizzeriacrud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

      @Autowired
      private IngredientRepository ingredientRepository;

      // INDEX
      @GetMapping
      public String index(Model model, @RequestParam("edit") Optional<Integer> ingredientId) {
            // recupero ingredienti
            List<Ingredient> ingredients = ingredientRepository.findAll();
            // e li passo
            model.addAttribute("ingredientList", ingredients);

            // inizializzo ingObj
            Ingredient ingObj;
            // se passo un id come parametro verifico se presente
            if (ingredientId.isPresent()) {
                  Optional<Ingredient> ingData = ingredientRepository.findById(ingredientId.get());
                  // se è presente valorizzo ingObj con ingrediente da db
                  if (ingData.isPresent()) {
                        ingObj = ingData.get();
                  } else {
                        // se non è presente valorizzo ingObj con un ingrediente vuoto
                        ingObj = new Ingredient();
                  }
            } else {
                  // se non passo id valorizzo ingObj con ingrediente vuoto
                  ingObj = new Ingredient();
            }
            // passo ingObj per mappare il form su un ingrediente
            model.addAttribute("ingObj", ingObj);
            return "/ingredients/index";
      }

      @PostMapping("/save")
      public String save(@Valid @ModelAttribute("ingObj") Ingredient formIngredient,
                         BindingResult bindingResult, Model model) {
            // verfichiamo se ci sono errori
            if (bindingResult.hasErrors()) {
                  model.addAttribute("ingredients", ingredientRepository.findAll());
                  return "/ingredients/index";
            }
            ingredientRepository.save(formIngredient);
            return "redirect:/ingredients";
      }

      @PostMapping("/delete/{id}")
      public String delete(@PathVariable Integer id) {
            // prima di eliminare ingrediente lo dissocio da pizze
            Optional<Ingredient> result = ingredientRepository.findById(id);
            if (result.isEmpty()) {
                  throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            // per ogni pizza associata rimuovo ingrediente
            for (Pizza pizza : result.get().getPizza()) {
                  pizza.getIngredients().remove(result.get());
            }

            ingredientRepository.deleteById(id);
            return "redirect:/ingredients";
      }
}

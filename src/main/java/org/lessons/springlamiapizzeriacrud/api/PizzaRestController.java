package org.lessons.springlamiapizzeriacrud.api;

import jakarta.validation.Valid;
import org.lessons.springlamiapizzeriacrud.model.Pizza;
import org.lessons.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/pizza")
public class PizzaRestController {

      @Autowired
      private PizzaRepository pizzaRepository;

      @GetMapping
      public List<Pizza> index() {
            return pizzaRepository.findAll();
      }

      @GetMapping("{id}")
      public ResponseEntity<Pizza> get(@PathVariable Integer id) {
            Optional<Pizza> pizza = pizzaRepository.findById(id);
            if (pizza.isPresent()) {
                  return new ResponseEntity<Pizza>(pizza.get(), HttpStatus.OK);
            } else {
                  return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
            }
      }

      @PostMapping
      public Pizza create(@Valid @RequestBody Pizza pizza) {
            return pizzaRepository.save(pizza);
      }

      @PutMapping("{id}")
      public Pizza update(@Valid @RequestBody Pizza pizza, @PathVariable Integer id) {
            pizza.setId(id);
            return pizzaRepository.save(pizza);
      }

      @DeleteMapping("{id}")
      public void delete(@PathVariable Integer id) {
            pizzaRepository.deleteById(id);
      }
}

package org.lessons.springlamiapizzeriacrud.controller;

import jakarta.validation.Valid;
import org.lessons.springlamiapizzeriacrud.model.Pizza;
import org.lessons.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizza")
public class PizzaController {

    @Autowired
    private PizzaRepository repository;

    // INDEX
    @GetMapping
    public String index(Model model, @RequestParam(name = "keyword", required = false) String search) {
        // inizializzo pizza
        List<Pizza> pizza;
        // se non ho param prendo tutto
        if(search == null || search.isBlank()) {
            pizza = repository.findAll();
        } else {
            // altrimenti query personalizzata
            pizza = repository.findByNameContainingIgnoreCase(search);
        }
        model.addAttribute("pizzaList", pizza);
        // mando valore di search per occupare campo di input search
        model.addAttribute("search", search == null ? "" : search);
        return "/pizza/index";
    }

    // SHOW
    @GetMapping("/{id}")
    public String show(Model model, @PathVariable Integer id) {
        // recupero pizza con id corrispondente
        Optional<Pizza> pizzaId = repository.findById(id);
        // se presente la recupero e la passo come attributo col model
        if (pizzaId.isPresent()) {
            Pizza pizzaShow = pizzaId.get();
            model.addAttribute("pizza", pizzaShow);
            return "/pizza/show";
        // altrimenti lancio eccezione
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza inesistente");
        }
    }

    // CREATE
    @GetMapping("/create")
    public String create(Model model) {
        // passo l'attributo pizza che è un oggetto Pizza vuoto
        model.addAttribute("pizza", new Pizza());
        return "/pizza/create";
    }

    // STORE
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        // controllo che il nome sia univoco
        if (!isUniqueName(formPizza)) {
            // aggiungo errore personalizzato in bindingResult
            bindingResult.addError(new FieldError("pizza", "name", formPizza.getName(), false, null, null , "Nome già presente in database"));
        }
        // controllo se ci sono stati e nel caso rimando a form create
        if (bindingResult.hasErrors()) {
            return "/pizza/create";
        }
        // setto data e ora di creazione al momento di creazione stessa
        formPizza.setCreatedAt(LocalDateTime.now());
        // metodo save crea se non trova corrispondenza altrimenti fa update
        repository.save(formPizza);
        return "redirect:/pizza";
    }

    // EDIT
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("pizza", repository.findById(id).get());
        return "/pizza/edit";
    }

    // UPDATE
    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/pizza/edit";
        }
        repository.save(formPizza);
        return "redirect:/pizza";
    }

    // DELETE
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        repository.deleteById(id);
        return "redirect:/pizza";  // redirect dopo delete di oggetto
    }

    //-------------------------------------------------

    // metodo che verifica presenza in database di un nome di pizza già presente
    private boolean isUniqueName(Pizza formPizza) {
        Optional<Pizza> result = repository.findByName(formPizza.getName());
        return result.isEmpty();
    }
}

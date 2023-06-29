package org.lessons.springlamiapizzeriacrud.controller;

import jakarta.validation.Valid;
import org.lessons.springlamiapizzeriacrud.messages.Alert;
import org.lessons.springlamiapizzeriacrud.messages.AlertType;
import org.lessons.springlamiapizzeriacrud.model.Offer;
import org.lessons.springlamiapizzeriacrud.model.Pizza;
import org.lessons.springlamiapizzeriacrud.repository.OfferRepository;
import org.lessons.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/offers")
public class OfferController {

    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private PizzaRepository pizzaRepository;

    // CREATE
    @GetMapping("/create")
    public String create(@RequestParam("pizzaId") Integer pizzaId, Model model) {
        // creo nuova offerta
        Offer offer = new Offer();
        // precarico pizza con quella passata come parametro
        Optional<Pizza> pizza = pizzaRepository.findById(pizzaId);
        if (pizza.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza inesistente");
        }
        offer.setPizza(pizza.get());
        model.addAttribute("offer", offer);
        return "/offers/create_edit";
    }

    // STORE
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("offer") Offer formOffer, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        // controllo se ci sono stati errori e nel caso rimando a form create
        if (bindingResult.hasErrors()) {
            return "/offers/create_edit";
        }
        // metodo save crea se non trova corrispondenza altrimenti fa update
        offerRepository.save(formOffer);
        // aggiungo alert di corretta aggiunta come flashAttribute
        redirectAttributes.addFlashAttribute("message", new Alert(AlertType.SUCCESS, formOffer.getTitle() + " creata correttamente."));
        return "redirect:/pizza/" + formOffer.getPizza().getId();
    }

    // EDIT
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        // recupero offerta tramite id
        Offer offer = getOfferById(id);
        model.addAttribute("offer", offer);
        return "/offers/create_edit";
    }

    // UPDATE
    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("offer") Offer formOffer, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        // recupero offerta pre modifica tramite id
        Offer offer = getOfferById(id);
        // controllo se ci sono stati errori e nel caso rimando a form create
        if (bindingResult.hasErrors()) {
            return "/offers/create_edit";
        }
        // aggiungo dati che non modifichiamo nel form
        formOffer.setId(offer.getId());
        // salvo modifiche
        offerRepository.save(formOffer);
        // aggiungo alert di corretta modifica come flashAttribute
        redirectAttributes.addFlashAttribute("message", new Alert(AlertType.SUCCESS, formOffer.getTitle() + " modificata correttamente."));
        return "redirect:/pizza/" + formOffer.getPizza().getId();
    }

    // DELETE
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        // recupero offerta pre modifica tramite id
        Offer offer = getOfferById(id);
        offerRepository.delete(offer);
        // aggiungo alert di corretto delete come flashAttribute
        redirectAttributes.addFlashAttribute("message", new Alert(AlertType.SUCCESS, offer.getTitle() + " eliminata correttamente."));
        // redirect dopo delete di oggetto
        return "redirect:/pizza/" + offer.getPizza().getId();
    }

    //--------------------------------------------------------

    // metodo per recuperare offerta da database tramite id
    private Offer getOfferById(Integer id) {
        // recupero pizza con id corrispondente
        Optional<Offer> offerId = offerRepository.findById(id);
        // se non esiste lancia eccezione
        if (offerId.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Offerta inesistente");
        }
        return offerId.get();
    }
}

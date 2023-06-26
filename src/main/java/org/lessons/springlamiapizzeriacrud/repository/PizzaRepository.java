package org.lessons.springlamiapizzeriacrud.repository;

import org.lessons.springlamiapizzeriacrud.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
    // metodo per cercare nomi che contengono la stringa passata senza considerare min a maiuscolo
    List<Pizza> findByNameContainingIgnoreCase(String name);
    // metodo per cercare pizza dal nome
    Optional<Pizza> findByName(String name);
}

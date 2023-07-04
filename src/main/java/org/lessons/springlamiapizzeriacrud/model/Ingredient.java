package org.lessons.springlamiapizzeriacrud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "ingredients")
public class Ingredient {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Integer id;

      @NotBlank(message = "Nome non può essere vuoto o nullo")
      @Column(nullable = false)
      private String name;

      @JsonIgnore
      @ManyToMany(mappedBy = "ingredients")
      private List<Pizza> pizza;

      public Integer getId() {
            return id;
      }

      public void setId(Integer id) {
            this.id = id;
      }

      public String getName() {
            return name;
      }

      public void setName(String title) {
            this.name = title;
      }

      public List<Pizza> getPizza() {
            return pizza;
      }

      public void setPizza(List<Pizza> pizza) {
            this.pizza = pizza;
      }
}

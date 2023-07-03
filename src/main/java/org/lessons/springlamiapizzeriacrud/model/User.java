package org.lessons.springlamiapizzeriacrud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Entity
@Table(name = "users")
public class User {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Integer id;
      @NotNull
      private String username;
      @NotNull
      private String email;
      @NotNull
      private String password;

      public Integer getId() {
            return id;
      }

      public void setId(Integer id) {
            this.id = id;
      }

      public String getUsername() {
            return username;
      }

      public void setUsername(String username) {
            this.username = username;
      }

      public String getEmail() {
            return email;
      }

      public void setEmail(String email) {
            this.email = email;
      }

      public String getPassword() {
            return password;
      }

      public void setPassword(String password) {
            this.password = password;
      }

      public Set<Role> getRoles() {
            return roles;
      }

      public void setRoles(Set<Role> roles) {
            this.roles = roles;
      }

      @ManyToMany(fetch = FetchType.EAGER)
      private Set<Role> roles;
}

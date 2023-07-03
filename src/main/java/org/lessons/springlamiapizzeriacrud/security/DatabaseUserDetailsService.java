package org.lessons.springlamiapizzeriacrud.security;

import org.lessons.springlamiapizzeriacrud.model.User;
import org.lessons.springlamiapizzeriacrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class DatabaseUserDetailsService implements UserDetailsService {

      @Autowired
      private UserRepository userRepository;

      @Override
      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            // recupero User da database tramite username
            Optional<User> user = userRepository.findByUsername(username);
            // se presente..
            if (user.isPresent()) {
                  // ..costruisco nuovo UserDetails dallo User recuperato
                  return new DatabaseUserDetails(user.get());
              // altrimenti lancio eccezione apposita
            } else throw new UsernameNotFoundException("Username " + username + " not found");
      }
}

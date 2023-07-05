package org.lessons.springlamiapizzeriacrud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

      // UserDetailsService NECESSARIO
      @Bean
      DatabaseUserDetailsService userDetailsService() {
            return new DatabaseUserDetailsService();
      }

      // PasswordEncoder NECESSARIO
      @Bean
      PasswordEncoder passwordEncoder() {
            return PasswordEncoderFactories.createDelegatingPasswordEncoder();
      }

      // AuthenticationProvider: UserDetailsService + PasswordEncoder
      @Bean
      DaoAuthenticationProvider authenticationProvider() {
            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

            authProvider.setUserDetailsService(userDetailsService());
            authProvider.setPasswordEncoder(passwordEncoder());

            return authProvider;
      }

      @Bean
      SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.authorizeHttpRequests()
                    .requestMatchers("/api/**").permitAll()
                    .requestMatchers("/ingredients").hasAuthority("ADMIN")
                    .requestMatchers("/pizza/create").hasAuthority("ADMIN")
                    .requestMatchers("/pizza/edit/**").hasAuthority("ADMIN")
                    .requestMatchers("/pizza/**").hasAnyAuthority("ADMIN", "USER")
                    .requestMatchers("/offers/**").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.POST).permitAll()
                    .requestMatchers("/**").permitAll()
                    .and().formLogin()
                    .and().logout();
            // disabilitiamo csrf per permettere chiamate api da postman
            http.csrf().disable();
            return http.build();
      }
}

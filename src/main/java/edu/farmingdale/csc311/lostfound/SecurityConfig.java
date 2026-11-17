package edu.farmingdale.csc311.lostfound;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // anyone can open these without logging in
                .requestMatchers("/register", "/login", "/h2-console/**").permitAll()
                // everything else needs a login
                .anyRequest().authenticated()
            )

            .formLogin(form -> form
                    .loginPage("/login")
                    .permitAll())
            .logout(logout -> logout.permitAll())
            // the H2 console doesn't send CSRF tokens and runs inside a frame
            .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        return http.build();
    }
}

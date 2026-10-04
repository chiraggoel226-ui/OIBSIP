package DigitalLibraryManagementSystem.config;

import DigitalLibraryManagementSystem.service.CustomUserDetailsService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                                "/",
                                "/index",
                                "/login",
                                "/register",
                                "/css/**",
                                "/js/**"
                        ).permitAll()

                        .requestMatchers("/admin/**")
                        .hasAuthority("ADMIN")

                        .requestMatchers("/user/**")
                        .hasAuthority("USER")

                        .anyRequest()
                        .authenticated()
                )

                .formLogin(form -> form

                        .loginPage("/login")

                        .successHandler((request, response, authentication) -> {

                            if (authentication.getAuthorities()
                                    .stream()
                                    .anyMatch(authority ->
                                            authority.getAuthority()
                                                    .equals("ADMIN"))) {

                                response.sendRedirect(
                                        "/admin/dashboard"
                                );

                            } else {

                                response.sendRedirect(
                                        "/user/dashboard"
                                );
                            }
                        })

                        .permitAll()
                )

                .logout(logout -> logout

                        .logoutSuccessUrl("/login?logout")

                        .permitAll()
                );

        return http.build();
    }
}
package cl.eva.springedumanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf
                .ignoringRequestMatchers(
                    "/h2-console/**",
                    "/api/**"
                )
            )

            .headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions.sameOrigin())
            )

            .authorizeHttpRequests(auth -> auth

                // Consola H2
                .requestMatchers("/h2-console/**").permitAll()

                // API REST
                .requestMatchers("/api/**")
                    .hasAnyRole("ADMIN", "USER")

                // Solo ADMIN puede registrar
                .requestMatchers("/cursos/nuevo").hasRole("ADMIN")
                .requestMatchers("/cursos/guardar").hasRole("ADMIN")

                .requestMatchers("/estudiantes/nuevo").hasRole("ADMIN")
                .requestMatchers("/estudiantes/guardar").hasRole("ADMIN")

                // ADMIN y USER pueden consultar
                .requestMatchers("/estudiantes/**")
                    .hasAnyRole("ADMIN", "USER")

                .requestMatchers("/cursos/**")
                    .hasAnyRole("ADMIN", "USER")

                .anyRequest().authenticated()
            )

            // Login web
            .formLogin(form -> form
                .defaultSuccessUrl("/estudiantes", true)
                .permitAll()
            )

            // Permite Basic Authentication para Thunder Client
            .httpBasic(httpBasic -> {})

            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(
            PasswordEncoder passwordEncoder) {

        UserDetails admin = User.builder()
            .username("admin")
            .password(passwordEncoder.encode("admin123"))
            .roles("ADMIN")
            .build();

        UserDetails usuario = User.builder()
            .username("usuario")
            .password(passwordEncoder.encode("user123"))
            .roles("USER")
            .build();

        return new InMemoryUserDetailsManager(admin, usuario);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return org.springframework.security.crypto.factory
            .PasswordEncoderFactories
            .createDelegatingPasswordEncoder();
    }
}
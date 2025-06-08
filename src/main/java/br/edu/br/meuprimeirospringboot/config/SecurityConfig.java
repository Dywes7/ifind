package br.edu.br.meuprimeirospringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/usuarios/cadastrar", "/usuarios/salvar" , "/css/**", "/js/**", "/image/**", "/webjars/**").permitAll()
                .requestMatchers("/alunos/cadastrar").hasRole("ADMIN")
                .requestMatchers("/disciplinas/cadastrar").hasRole("ADMIN")
                .requestMatchers("/turmas/cadastrar").hasRole("ADMIN")
                .requestMatchers("/matriculas/cadastrar").hasRole("ADMIN")
                .requestMatchers("/semestres/cadastrar").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")                     
                .logoutSuccessUrl("/login?logout=true")  
                .invalidateHttpSession(true)             
                .deleteCookies("JSESSIONID")  
                .clearAuthentication(true)
            )
            .formLogin(form -> form          
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .permitAll()
                .defaultSuccessUrl("/", true)
            )
            .httpBasic(httpBasic -> httpBasic.disable());

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}

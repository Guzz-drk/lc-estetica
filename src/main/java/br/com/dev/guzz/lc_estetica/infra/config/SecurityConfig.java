package br.com.dev.guzz.lc_estetica.infra.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.dev.guzz.lc_estetica.constants.HttpConstant;
import br.com.dev.guzz.lc_estetica.constants.UserConstant;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers(HttpMethod.POST, HttpConstant.AUTH+"/login").permitAll()
                    .requestMatchers(HttpMethod.POST, HttpConstant.USERS+"/register").permitAll()
                    .requestMatchers(HttpMethod.POST, HttpConstant.CLIENTS+"/**").hasRole(UserConstant.STAFF)
                    .requestMatchers(HttpMethod.GET, HttpConstant.CLIENTS+"/**").hasRole(UserConstant.STAFF)
                    .requestMatchers(HttpMethod.POST, HttpConstant.CATEGORIES+"/**").hasRole(UserConstant.STAFF)
                    .requestMatchers(HttpMethod.GET, HttpConstant.CATEGORIES+"/**").hasRole(UserConstant.STAFF)
                    .requestMatchers(HttpMethod.POST, HttpConstant.SERVICES+"/**").hasRole(UserConstant.STAFF)
                    .requestMatchers(HttpMethod.GET, HttpConstant.SERVICES+"/**").hasRole(UserConstant.STAFF)
                    .requestMatchers(HttpMethod.POST, HttpConstant.CUSTOMER_ORDERS+"/**").hasRole(UserConstant.STAFF)
                    .requestMatchers(HttpMethod.GET, HttpConstant.CUSTOMER_ORDERS+"/**").hasRole(UserConstant.STAFF)
                    .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

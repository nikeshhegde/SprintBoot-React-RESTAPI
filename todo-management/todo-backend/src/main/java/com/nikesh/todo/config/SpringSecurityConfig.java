package com.nikesh.todo.config;

import com.nikesh.todo.security.JwtAuthenticationEntryPoint;
import com.nikesh.todo.security.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig {

    private UserDetailsService userDetailsService;

    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    private static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration){
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity httpSecurity){

        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> {
//                    authorize.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN","USER");
//                    authorize.requestMatchers(HttpMethod.PATCH, "/api/**").hasAnyRole("ADMIN","USER");
                    //authorize.requestMatchers(HttpMethod.GET,"/api/**").permitAll();
                    authorize.requestMatchers("/api/auth/**").permitAll();
                    authorize.requestMatchers(HttpMethod.OPTIONS,"/**").permitAll();
                    authorize.anyRequest().authenticated();
                })
                .httpBasic(Customizer.withDefaults());

        httpSecurity.exceptionHandling(exception -> exception
                .authenticationEntryPoint(jwtAuthenticationEntryPoint));

        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    //In Memory authentication
   /* @Bean
    public UserDetailsService userDetailsService(){

        UserDetails nikesh = User.builder()
                .username("nikesh")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(nikesh, admin);
    }*/
}

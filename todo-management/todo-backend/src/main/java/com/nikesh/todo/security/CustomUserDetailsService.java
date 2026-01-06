package com.nikesh.todo.security;

import com.nikesh.todo.entity.User;
import com.nikesh.todo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(@NonNull String usernameOrEmail) throws UsernameNotFoundException {

        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User does not exist by Username or Email :"+ usernameOrEmail));


        Set<GrantedAuthority> authoritySet = user.getRoles().stream()
                .map((role -> new SimpleGrantedAuthority(role.getName())))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                usernameOrEmail,
                user.getPassword(),
                authoritySet
        );
    }
}

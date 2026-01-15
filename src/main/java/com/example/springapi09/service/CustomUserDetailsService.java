package com.example.springapi09.service;


import com.example.springapi09.repository.AdminRepository;
import com.example.springapi09.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String name)
            throws UsernameNotFoundException {

        return adminRepository.findByName(name)
                .map(admin -> new org.springframework.security.core.userdetails.User(
                        admin.getName(),
                        admin.getPassword(),
                        List.of(new SimpleGrantedAuthority(
                                "ROLE_" + admin.getRole().getName().toUpperCase()
                        ))
                ))

                .orElseGet(() -> userRepository.findByName(name)
                        .map(user -> new org.springframework.security.core.userdetails.User(
                                user.getName(),
                                user.getPassword(),
                                Collections.emptyList()
                        ))
                        .orElseThrow(() ->
                                new UsernameNotFoundException(
                                        "User/Admin not found with name: " + name
                                )
                        )
                );
    }

}

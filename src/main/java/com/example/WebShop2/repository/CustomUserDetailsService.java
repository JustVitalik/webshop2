package com.example.WebShop2.repository;


import com.example.WebShop2.model.CustomUserDetails;
import com.example.WebShop2.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> users= userRepository.findUserByEmail(email);
        users.orElseThrow(()-> new UsernameNotFoundException("404"));
        return users.map(CustomUserDetails::new).get();
    }
}

package com.example.shopall_challenge.security;

import com.example.shopall_challenge.model.Usuario;
import com.example.shopall_challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImp implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario user = userRepository.
                findOneByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " does not exist."));
        return new UserDetailsImp(user);
    }
}

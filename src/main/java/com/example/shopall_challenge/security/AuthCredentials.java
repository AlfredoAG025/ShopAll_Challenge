package com.example.shopall_challenge.security;

import com.example.shopall_challenge.model.Rol;
import lombok.Data;

import java.util.Set;

@Data
public class AuthCredentials {
    private String email;
    private String password;
}

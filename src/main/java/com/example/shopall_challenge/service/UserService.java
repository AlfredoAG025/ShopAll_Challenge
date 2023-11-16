package com.example.shopall_challenge.service;


import com.example.shopall_challenge.domain.GenericResponse;
import com.example.shopall_challenge.model.Usuario;
import com.example.shopall_challenge.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository){
        this.repository = repository;
    }

    public GenericResponse getUsers(){
        List<Usuario> users = this.repository.findAll();
        GenericResponse response =  new GenericResponse(200, "Correct", users);
        return response;
    }

    public GenericResponse getUser(@PathVariable Long user_id){
        List<Usuario> users = new ArrayList<>();
        Usuario user;
        Optional<Usuario> user_opt = repository.findById(user_id);
        GenericResponse response = null;

        if (user_opt.isPresent()){
            user = user_opt.get();
            users.add(user);
            response =  new GenericResponse(201, "User found", users);
        } else {
            response =  new GenericResponse(409, "User not found", users);
        }

        return response;
    }

    public GenericResponse updateUser(@PathVariable Long user_id, @RequestBody Usuario body){
        List<Usuario> users = new ArrayList<>();
        Usuario user;
        Optional<Usuario> user_opt = repository.findById(user_id);
        GenericResponse response = null;

        if (user_opt.isPresent()){
            user = body;
            user.setContrasena(passwordEncoder.encode(user.getContrasena()));
            users.add(user);
            repository.save(user);
            response =  new GenericResponse(201, "User Updated!", users);
        } else {
            response =  new GenericResponse(409, "User not found", users);
        }

        return response;
    }

    public GenericResponse addUser(@RequestBody Usuario body){
        List<Usuario> users = new ArrayList<>();
        body.setContrasena(passwordEncoder.encode(body.getContrasena()));
        users.add(body);
        GenericResponse response;
        try{
            repository.save(body);
            response =  new GenericResponse(201, "Accepted", users);
        } catch (Exception e){
            response =  new GenericResponse(409, "Conflict", users);
        }
        return response;
    }

    public GenericResponse deleteUser(@PathVariable Long user_id){
        List<Usuario> users = new ArrayList<>();
        Usuario user;
        Optional<Usuario> user_opt = repository.findById(user_id);
        GenericResponse response = null;

        if (user_opt.isPresent()){
            user = user_opt.get();
            users.add(user);
            repository.deleteById(user_id);
            response =  new GenericResponse(201, "User with id: " + user_id + " deleted", users);
        } else {
            response =  new GenericResponse(409, "User not found", users);
        }

        return response;
    }
}

package com.example.shopall_challenge.controller;

import com.example.shopall_challenge.domain.GenericResponse;
import com.example.shopall_challenge.model.Usuario;
import com.example.shopall_challenge.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    private final UserService service;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService service){
        LOGGER.debug("Init User Controller");
        this.service = service;
    }

    @GetMapping()
    public GenericResponse getUsers(){
        return this.service.getUsers();
    }

    @GetMapping("{user_id}")
    public GenericResponse getUserById(@PathVariable("user_id") Long user_id){
        return this.service.getUser(user_id);
    }


    @PostMapping("/add")
    public GenericResponse addUser(@RequestBody Usuario body){
        return this.service.addUser(body);
    }

    @DeleteMapping("/delete/{user_id}")
    public GenericResponse deleteUser(@PathVariable("user_id") Long user_id){
        return this.service.deleteUser(user_id);
    }

    @PutMapping("/update/{user_id}")
    public GenericResponse updateUser(@PathVariable("user_id") Long user_id, @RequestBody Usuario body){
        return this.service.updateUser(user_id, body);
    }


}

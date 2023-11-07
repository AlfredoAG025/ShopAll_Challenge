package com.example.shopall_challenge.controller;

import com.example.shopall_challenge.domain.GenericResponse;
import com.example.shopall_challenge.model.Rol;
import com.example.shopall_challenge.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/role")
public class RoleController {

    private final RoleService service;
    @Autowired
    public RoleController(RoleService service){
        this.service = service;
    }

    @GetMapping
    public GenericResponse getRole(){
        return this.service.getRole();
    }

    @GetMapping("{rol_id}")
    public GenericResponse getRoleById(@PathVariable("rol_id") Long rol_id){
        return this.service.getRole(rol_id);
    }

    @PostMapping("/add")
    public GenericResponse addRole(@RequestBody Rol body){
        return this.service.addRole(body);
    }

    @PutMapping("/update/{rol_id}")
    public GenericResponse updateRole(@PathVariable("rol_id") Long rol_id, @RequestBody Rol body){
        return this.service.updateRole(rol_id, body);
    }

    @DeleteMapping("/delete/{rol_id}")
    public GenericResponse deleteRole(@PathVariable("rol_id") Long rol_id){
        return this.service.deleteRole(rol_id);
    }
}


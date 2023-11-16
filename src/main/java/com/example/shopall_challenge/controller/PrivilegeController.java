package com.example.shopall_challenge.controller;

import com.example.shopall_challenge.domain.GenericResponse;
import com.example.shopall_challenge.model.Privilegio;
import com.example.shopall_challenge.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/privilege")
@PreAuthorize("hasRole('ADMIN')")
public class PrivilegeController {

    private final PrivilegeService service;
    @Autowired
    public PrivilegeController(PrivilegeService service){
        this.service = service;
    }

    @GetMapping
    public GenericResponse getPrivilege(){
        return this.service.getPrivilege();
    }

    @GetMapping("{privilegio_id}")
    public GenericResponse getPrivilegeById(@PathVariable("privilegio_id") Long privilegio_id){
        return this.service.getPrivilege(privilegio_id);
    }

    @PostMapping("/add")
    public GenericResponse addPrivilege(@RequestBody Privilegio body){
        return this.service.addPrivilege(body);
    }

    @PutMapping("/update/{privilegio_id}")
    public GenericResponse updatePrivilege(@PathVariable("privilegio_id") Long privilegio_id, @RequestBody Privilegio body){
        return this.service.updatePrivilege(privilegio_id, body);
    }

    @DeleteMapping("/delete/{privilegio_id}")
    public GenericResponse deletePrivilege(@PathVariable("privilegio_id") Long privilegio_id){
        return this.service.deletePrivilege(privilegio_id);
    }
}



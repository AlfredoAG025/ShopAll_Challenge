package com.example.shopall_challenge.service;
import com.example.shopall_challenge.domain.GenericResponse;
import com.example.shopall_challenge.model.Rol;
import com.example.shopall_challenge.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {


    private RoleRepository repository;
    public RoleService(RoleRepository roleRepository) {
        this.repository = roleRepository;

    }
    public GenericResponse getRole(){
        List<Rol> roles = this.repository.findAll();
        GenericResponse response =  new GenericResponse(200, "Correct", roles);
        return response;
    }

    public GenericResponse getRole(@PathVariable Long rol_id){
        List<Rol> rols = new ArrayList<>();
        Rol rol;
        Optional<Rol> role_opt = repository.findById(rol_id);
        GenericResponse response = null;

        if (role_opt.isPresent()){
            rol = role_opt.get();
            rols.add(rol);
            response =  new GenericResponse(201, "Role found", rols);
        } else {
            response =  new GenericResponse(409, "Role not found", rols);
        }

        return response;
    }

    public GenericResponse addRole(@RequestBody Rol body){
        List<Rol> rols = new ArrayList<>();
        rols.add(body);

        GenericResponse response;
        try{
            repository.save(body);
            response =  new GenericResponse(201, "Accepted", rols);
        } catch (Exception e){
            response =  new GenericResponse(409, "Conflict", rols);
        }
        return response;
    }

    public GenericResponse updateRole(@PathVariable Long rol_id, @RequestBody Rol body){
        List<Rol> rols = new ArrayList<>();
        Rol rol;
        Optional<Rol> review_opt = repository.findById(rol_id);
        GenericResponse response = null;

        if (review_opt.isPresent()){
            rol = body;
            rols.add(rol);
            repository.save(rol);
            response =  new GenericResponse(201, "Roles Updated!", rols);
        } else {
            response =  new GenericResponse(409, "Roles not found", rols);
        }
        return response;
    }

    public GenericResponse deleteRole(@PathVariable Long role_id){
        List<Rol> rols = new ArrayList<>();
        Rol rol;
        Optional<Rol> role_opt = repository.findById(role_id);
        GenericResponse response = null;

        if (role_opt.isPresent()){
            rol = role_opt.get();
            rols.add(rol);
            repository.deleteById(role_id);
            response =  new GenericResponse(201, "Role with id: " + role_id + " deleted", rols);
        } else {
            response =  new GenericResponse(409, "Role not found", rols);
        }

        return response;
    }

}


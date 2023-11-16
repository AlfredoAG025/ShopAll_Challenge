package com.example.shopall_challenge.service;
import com.example.shopall_challenge.domain.GenericResponse;
import com.example.shopall_challenge.model.Privilegio;
import com.example.shopall_challenge.model.Rol;
import com.example.shopall_challenge.repository.PrivilegeRepository;
import com.example.shopall_challenge.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PrivilegeService {


    private PrivilegeRepository repository;
    public PrivilegeService(PrivilegeRepository privilegeRepository) {
        this.repository = privilegeRepository;

    }
    public GenericResponse getPrivilege(){
        List<Privilegio> privilegios = this.repository.findAll();
        GenericResponse response =  new GenericResponse(200, "Correct", privilegios);
        return response;
    }

    public GenericResponse getPrivilege(@PathVariable Long privilegio_id){
        List<Privilegio> privilegios = new ArrayList<>();
        Privilegio privilegio;
        Optional<Privilegio> privilege_opt = repository.findById(privilegio_id);
        GenericResponse response = null;

        if (privilege_opt.isPresent()){
            privilegio = privilege_opt.get();
            privilegios.add(privilegio);
            response =  new GenericResponse(201, "Privilege found", privilegios);
        } else {
            response =  new GenericResponse(409, "Privilege not found", privilegios);
        }

        return response;
    }

    public GenericResponse addPrivilege(@RequestBody Privilegio body){
        List<Privilegio> privilegios = new ArrayList<>();
        privilegios.add(body);

        GenericResponse response;
        try{
            repository.save(body);
            response =  new GenericResponse(201, "Accepted", privilegios);
        } catch (Exception e){
            response =  new GenericResponse(409, "Conflict", privilegios);
        }
        return response;
    }

    public GenericResponse updatePrivilege(@PathVariable Long privilegio_id, @RequestBody Privilegio body){
        List<Privilegio> privilegios = new ArrayList<>();
        Privilegio privilegio;
        Optional<Privilegio> privilege_opt = repository.findById(privilegio_id);
        GenericResponse response = null;

        if (privilege_opt.isPresent()){
            privilegio = body;
            privilegios.add(privilegio);
            repository.save(privilegio);
            response =  new GenericResponse(201, "Privileges Updated!", privilegios);
        } else {
            response =  new GenericResponse(409, "Privileges not found", privilegios);
        }
        return response;
    }

    public GenericResponse deletePrivilege(@PathVariable Long privilege_id){
        List<Privilegio> privilegios = new ArrayList<>();
        Privilegio privilegio;
        Optional<Privilegio> privilege_opt = repository.findById(privilege_id);
        GenericResponse response = null;

        if (privilege_opt.isPresent()){
            privilegio = privilege_opt.get();
            privilegios.add(privilegio);
            repository.deleteById(privilege_id);
            response =  new GenericResponse(201, "Privilege with id: " + privilege_id + " deleted", privilegios);
        } else {
            response =  new GenericResponse(409, "Privilege not found", privilegios);
        }

        return response;
    }

}

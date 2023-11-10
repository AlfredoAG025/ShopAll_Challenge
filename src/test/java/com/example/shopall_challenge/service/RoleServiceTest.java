package com.example.shopall_challenge.service;
import com.example.shopall_challenge.domain.GenericResponse;
import com.example.shopall_challenge.model.Rol;
import com.example.shopall_challenge.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    private Rol rol;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        rol = new Rol();
        rol.setRol_id(1L);
        rol.setRol("ROLE_USER");
    }

    @Test
    void testGetRole() {
        List<Rol> roles = new ArrayList<>();
        roles.add(rol);

        when(roleRepository.findAll()).thenReturn(roles);

        GenericResponse response = roleService.getRole();

        assertNotNull(response);
        assertEquals(200, response.getCode());
        assertEquals("Correct", response.getMessage());
        assertEquals(roles, response.getData());
    }

    @Test
    void testGetRoleById_RoleFound() {
        Optional<Rol> rolOptional = Optional.of(rol);
        when(roleRepository.findById(anyLong())).thenReturn(rolOptional);

        GenericResponse response = roleService.getRole(1L);

        assertNotNull(response);
        assertEquals(201, response.getCode());
        assertEquals("Role found", response.getMessage());
        List<Rol> roles = new ArrayList<>();
        roles.add(rol);
        assertEquals(roles, response.getData());
    }

    @Test
    void testGetRoleById_RoleNotFound() {
        Optional<Rol> rolOptional = Optional.empty();
        when(roleRepository.findById(anyLong())).thenReturn(rolOptional);

        GenericResponse response = roleService.getRole(1L);

        assertNotNull(response);
        assertEquals(409, response.getCode());
        assertEquals("Role not found", response.getMessage());
        assertEquals(new ArrayList<>(), response.getData());
    }

    @Test
    void testAddRole() {
        List<Rol> roles = new ArrayList<>();
        roles.add(rol);

        when(roleRepository.save(any())).thenReturn(rol);

        GenericResponse response = roleService.addRole(rol);

        assertNotNull(response);
        assertEquals(201, response.getCode());
        assertEquals("Accepted", response.getMessage());
        assertEquals(roles, response.getData());
    }

    @Test
    void testUpdateRole_RoleUpdated() {
        Optional<Rol> rolOptional = Optional.of(rol);
        when(roleRepository.findById(anyLong())).thenReturn(rolOptional);

        Rol updatedRol = new Rol();
        updatedRol.setRol_id(1L);
        updatedRol.setRol("ROLE_ADMIN");

        GenericResponse response = roleService.updateRole(1L, updatedRol);

        assertNotNull(response);
        assertEquals(201, response.getCode());
        assertEquals("Roles Updated!", response.getMessage());
        List<Rol> roles = new ArrayList<>();
        roles.add(updatedRol);
        assertEquals(roles, response.getData());
    }

    @Test
    void testUpdateRole_RoleNotFound() {
        Optional<Rol> rolOptional = Optional.empty();
        when(roleRepository.findById(anyLong())).thenReturn(rolOptional);

        Rol updatedRol = new Rol();
        updatedRol.setRol_id(1L);
        updatedRol.setRol("ROLE_ADMIN");

        GenericResponse response = roleService.updateRole(1L, updatedRol);

        assertNotNull(response);
        assertEquals(409, response.getCode());
        assertEquals("Roles not found", response.getMessage());
        assertEquals(new ArrayList<>(), response.getData());
    }

    @Test
    void testDeleteRole_RoleDeleted() {
        Optional<Rol> rolOptional = Optional.of(rol);
        when(roleRepository.findById(anyLong())).thenReturn(rolOptional);

        GenericResponse response = roleService.deleteRole(1L);

        assertNotNull(response);
        assertEquals(201, response.getCode());
        assertEquals("Role with id: 1 deleted", response.getMessage());
        List<Rol> roles = new ArrayList<>();
        roles.add(rol);
        assertEquals(roles, response.getData());
    }

    @Test
    void testDeleteRole_RoleNotFound() {
        Optional<Rol> rolOptional = Optional.empty();
        when(roleRepository.findById(anyLong())).thenReturn(rolOptional);

        GenericResponse response = roleService.deleteRole(1L);

        assertNotNull(response);
        assertEquals(409, response.getCode());
        assertEquals("Role not found", response.getMessage());
        assertEquals(new ArrayList<>(), response.getData());
    }
}
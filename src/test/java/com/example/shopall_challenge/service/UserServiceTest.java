package com.example.shopall_challenge.service;
import com.example.shopall_challenge.domain.GenericResponse;
import com.example.shopall_challenge.model.Usuario;
import com.example.shopall_challenge.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        usuario = new Usuario();
        usuario.setUsuario_id(1L);
        usuario.setNombre_usuario("john_doe");
        usuario.setEmail("john.doe@example.com");
        usuario.setContrasena("password");
        usuario.setTipo("USER");
    }

    @Test
    void testGetUsers() {
        List<Usuario> users = new ArrayList<>();
        users.add(usuario);

        when(userRepository.findAll()).thenReturn(users);

        GenericResponse response = userService.getUsers();

        assertNotNull(response);
        assertEquals(200, response.getCode());
        assertEquals("Correct", response.getMessage());
        assertEquals(users, response.getData());
    }

    @Test
    void testGetUser_UserFound() {
        Optional<Usuario> userOptional = Optional.of(usuario);
        when(userRepository.findById(anyLong())).thenReturn(userOptional);

        GenericResponse response = userService.getUser(1L);

        assertNotNull(response);
        assertEquals(201, response.getCode());
        assertEquals("User found", response.getMessage());
        List<Usuario> users = new ArrayList<>();
        users.add(usuario);
        assertEquals(users, response.getData());
    }

    @Test
    void testGetUser_UserNotFound() {
        Optional<Usuario> userOptional = Optional.empty();
        when(userRepository.findById(anyLong())).thenReturn(userOptional);

        GenericResponse response = userService.getUser(1L);

        assertNotNull(response);
        assertEquals(409, response.getCode());
        assertEquals("User not found", response.getMessage());
        assertEquals(new ArrayList<>(), response.getData());
    }

    @Test
    void testUpdateUser_UserUpdated() {
        Optional<Usuario> userOptional = Optional.of(usuario);
        when(userRepository.findById(anyLong())).thenReturn(userOptional);
        when(userRepository.save(any())).thenReturn(usuario);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        Usuario updatedUser = new Usuario();
        updatedUser.setUsuario_id(1L);
        updatedUser.setNombre_usuario("john_doe_updated");
        updatedUser.setEmail("john.doe.updated@example.com");
        updatedUser.setContrasena("newPassword");
        updatedUser.setTipo("ADMIN");

        GenericResponse response = userService.updateUser(1L, updatedUser);

        assertNotNull(response);
        assertEquals(201, response.getCode());
        assertEquals("User Updated!", response.getMessage());
        List<Usuario> users = new ArrayList<>();
        users.add(updatedUser);
        assertEquals(users, response.getData());
    }

    @Test
    void testUpdateUser_UserNotFound() {
        Optional<Usuario> userOptional = Optional.empty();
        when(userRepository.findById(anyLong())).thenReturn(userOptional);

        Usuario updatedUser = new Usuario();
        updatedUser.setUsuario_id(1L);
        updatedUser.setNombre_usuario("john_doe_updated");
        updatedUser.setEmail("john.doe.updated@example.com");
        updatedUser.setContrasena("newPassword");
        updatedUser.setTipo("ADMIN");

        GenericResponse response = userService.updateUser(1L, updatedUser);

        assertNotNull(response);
        assertEquals(409, response.getCode());
        assertEquals("User not found", response.getMessage());
        assertEquals(new ArrayList<>(), response.getData());
    }

    @Test
    void testAddUser() {
        List<Usuario> users = new ArrayList<>();
        users.add(usuario);

        when(userRepository.save(any())).thenReturn(usuario);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        GenericResponse response = userService.addUser(usuario);

        assertNotNull(response);
        assertEquals(201, response.getCode());
        assertEquals("Accepted", response.getMessage());
        assertEquals(users, response.getData());
    }

    @Test
    void testDeleteUser_UserDeleted() {
        Optional<Usuario> userOptional = Optional.of(usuario);
        when(userRepository.findById(anyLong())).thenReturn(userOptional);

        GenericResponse response = userService.deleteUser(1L);

        assertNotNull(response);
        assertEquals(201, response.getCode());
        assertEquals("User with id: 1 deleted", response.getMessage());
        List<Usuario> users = new ArrayList<>();
        users.add(usuario);
        assertEquals(users, response.getData());
    }

    @Test
    void testDeleteUser_UserNotFound() {
        Optional<Usuario> userOptional = Optional.empty();
        when(userRepository.findById(anyLong())).thenReturn(userOptional);

        GenericResponse response = userService.deleteUser(1L);

        assertNotNull(response);
        assertEquals(409, response.getCode());
        assertEquals("User not found", response.getMessage());
        assertEquals(new ArrayList<>(), response.getData());
    }
}
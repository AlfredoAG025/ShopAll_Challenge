package com.example.shopall_challenge.service;

import com.example.shopall_challenge.model.Privilegio;
import com.example.shopall_challenge.repository.PrivilegeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PrivilegeServiceTest {

    @Mock
    private PrivilegeRepository repository;

    @InjectMocks
    private PrivilegeService privilegeService;

    private Privilegio privilegio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        privilegio= new Privilegio();
        privilegio.setPrivilegio("Privilegio lectura");
        privilegio.setPrivilegio_id(12L);
    }

    @Test
    void getPrivilege() {
        when(repository.findAll()).thenReturn(Arrays.asList(privilegio));
        assertNotNull(privilegeService.getPrivilege());
    }
}
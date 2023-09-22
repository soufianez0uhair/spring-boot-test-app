package com.example.springfullproject2.services;

import com.example.springfullproject2.dto.GenerateTokenUserDTO;
import com.example.springfullproject2.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
    }

    @Test
    void itShouldGenerateToken() {
        // Given
        List<Role> roles = List.of(new Role("USER"));
        GenerateTokenUserDTO generateTokenUserDTO = new GenerateTokenUserDTO("user@user.com", roles);
        // When
        String token = jwtService.generateToken(generateTokenUserDTO);
        // Then
        assertNotNull(token);
        assertTrue(token.length() > 0);
    }
}
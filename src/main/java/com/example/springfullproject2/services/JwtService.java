package com.example.springfullproject2.services;

import com.example.springfullproject2.dto.GenerateTokenUserDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;

@Service
public class JwtService {

    private final String SECRET_KEY = "d68f18b49b2e88f68a7c3d6f028efecaa160a22ca7130ba084b0789ddad7a3e1";

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            GenerateTokenUserDTO userDTO
    ) {

        return Jwts
                .builder()
                .claim("roles", userDTO.getRoles().toString())
                .setClaims(extraClaims)
                .setSubject(userDTO.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(GenerateTokenUserDTO userDTO) {
        return generateToken(new HashMap<>(), userDTO);
    }
}

package com.example.springfullproject2.dto;

import com.example.springfullproject2.model.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Builder
@Getter
public class GenerateTokenUserDTO {

    private final String email;
    private final List<Role> roles;

}

package com.example.springfullproject2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(
        name = "roles"
)
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue
    private Long id;

    @Size(max = 30)
    @Column(
            nullable = false
    )
    private String authority;

    public Role() {
    }

    public Role(String authority) {
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}

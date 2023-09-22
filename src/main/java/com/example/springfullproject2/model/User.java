package com.example.springfullproject2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Table(
        name = "users"
)
public class User implements UserDetails {
    // check updatable
    @Id
    @GeneratedValue
    private Long id;

    // chouf wach first_
    @NotNull(message = "First Name is required.")
    @Size(max = 30)
    @Column(
            nullable = false
    )
    private String firstName;

    @NotNull(message = "Last Name is required.")
    @Size(max = 30)
    @Column(
            nullable = false
    )
    private String lastName;

    // chouf wach first_
    @NotNull(message = "Email is required.")
    @Size(max = 254)
    @Email(message = "Invalid email.")
    @Column(
            nullable = false
    )
    private String email;

    @NotNull(message = "Phone Number is required.")
    @Size(max = 30)
    @Column(
            nullable = false
    )
    private String phoneNumber;

    @NotNull(message = "Password is required.")
    @Size(min = 12)
    @Column(
            nullable = false
    )
    private String password;

    public User() {
    }

    public User( String firstName,
                 String lastName,
                 String email,
                 String phoneNumber,
                 String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

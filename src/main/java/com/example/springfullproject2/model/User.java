package com.example.springfullproject2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "user_email_unique", columnNames = "email"),
                @UniqueConstraint(name = "user_phone_number_unique", columnNames = "phone_number"),
        }
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
            name = "first_name",
            nullable = false
    )
    private String firstName;

    @NotNull(message = "Last Name is required.")
    @Size(max = 30)
    @Column(
            name = "last_name",
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
            name = "phone_number",
            nullable = false
    )
    private String phoneNumber;

    @NotNull(message = "Password is required.")
    @Size(min = 12)
    @Column(
            nullable = false
    )
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            foreignKey = @ForeignKey(name = "fk_users_roles_users"),
            inverseForeignKey = @ForeignKey(name = "fk_users_roles_roles")
    )
    private List<Role> roles = new ArrayList<Role>();

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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
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
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void addRole(Role role) {
        if(roles.contains(role)) {
            throw new IllegalArgumentException("Role already exists: " + role.getAuthority());
        }
        roles.add(role);
    }

    public void removeRole(Role role) {
        if(!roles.contains(role)) {
            throw new IllegalArgumentException("Role does not exist: " + role.getAuthority());
        }
        roles.remove(role);
    }
}

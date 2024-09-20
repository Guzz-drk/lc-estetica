package br.com.dev.guzz.lc_estetica.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.dev.guzz.lc_estetica.constants.UserConstant;
import br.com.dev.guzz.lc_estetica.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder

@Entity(name = "users")
@Table(schema = "estetica", name = "users")
public class Users implements UserDetails{
   
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    @NotEmpty(message = "Name cannot be null or empty")
    private String name;

    @Column(name = "login", unique = true, nullable = false)
    @NotEmpty(message = "Login cannot be null or empty")
    private String login;

    @Column(name = "password", nullable = false)
    @NotEmpty(message = "Password cannot be null or empty")
    private String password;

    @Column(name = "mail", unique = true, nullable = false)
    @NotEmpty(message = "Mail cannot be null or empty")
    @Email(message = "Enter a valid value for the mail")
    private String mail;

    @Column(name = "role")
    @Enumerated(EnumType.ORDINAL)
    private UserRole role;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Users(String name, String login, String password, String mail, UserRole role) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.mail = mail;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN)
            return List.of(new SimpleGrantedAuthority(UserConstant.ROLE_ADMIN), new SimpleGrantedAuthority(UserConstant.ROLE_STAFF), 
            new SimpleGrantedAuthority(UserConstant.ROLE_EMPLOYEE), new SimpleGrantedAuthority(UserConstant.ROLE_CLIENT));
        else if(this.role == UserRole.STAFF)
            return List.of(new SimpleGrantedAuthority(UserConstant.ROLE_STAFF), 
            new SimpleGrantedAuthority(UserConstant.ROLE_EMPLOYEE), new SimpleGrantedAuthority(UserConstant.ROLE_CLIENT));
        else if(this.role == UserRole.EMPLOYEE)
            return List.of(new SimpleGrantedAuthority(UserConstant.ROLE_EMPLOYEE), new SimpleGrantedAuthority(UserConstant.ROLE_CLIENT));
        else 
            return List.of(new SimpleGrantedAuthority(UserConstant.ROLE_CLIENT));
    }

    @Override
    public String getUsername() {
        return login;
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
}

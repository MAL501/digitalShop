package com.shop.wallapop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name="users")
@ToString(exclude = "userAds")
public class Usuario implements UserDetails {
    //No añadir import de ID da fallo
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "El nombre es obligatorio")
    @Column(unique = true, nullable = false)
    private String username;
    private String password;
    @Email
    @NotNull(message = "El email es obligatorio")
    private String email;
    private String phone;
    private String poblation;
    @OneToMany(targetEntity = Advertisement.class,cascade = CascadeType.ALL,
        mappedBy="usuario")
    private List<Advertisement> userAds=new ArrayList<>();
    private String rol;

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
}

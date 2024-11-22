package com.shop.wallapop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name="users")
@ToString(exclude = "userAds")
public class User {
    //No añadir import de ID da fallo
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "El nombre es obligatorio")
    @Column(unique = true, nullable = false)
    private String username;
    private String password;
    @Email
    private String email;
    private String phone;
    private String poblation;
    @OneToMany(targetEntity = Advertisement.class,cascade = CascadeType.ALL,
        mappedBy="user")
    private List<Advertisement> userAds=new ArrayList<>();

}

package com.shop.wallapop.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name="adverts")
@ToString(exclude = "adsPics")

public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Double price;
    private String description;
    private LocalDateTime createdAt;
    @ManyToOne(targetEntity = User.class)
    private User user;
    @OneToMany(targetEntity = Picture.class,cascade = CascadeType.ALL,
            mappedBy="advertisement")
    private List<Picture> adsPics=new ArrayList<>();

}
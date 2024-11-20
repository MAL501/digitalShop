package com.shop.wallapop.DTO;

import com.shop.wallapop.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvertDTO {
private Long id;
    private String title;
    private Double price;
    private String description;
    private LocalDateTime createdAt;
    private UserDTO user;
}

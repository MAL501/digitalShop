package com.shop.wallapop.DTO;

import com.shop.wallapop.entity.Advertisement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PictureDTO {
    private Long id;
    private String name;
    private Advertisement advertisement;
}

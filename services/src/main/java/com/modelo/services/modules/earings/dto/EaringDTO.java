package com.modelo.services.modules.earings.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EaringDTO {

    private String model;
    private String color;
    private Integer size;
    private String edition;
    private String material;
    
}

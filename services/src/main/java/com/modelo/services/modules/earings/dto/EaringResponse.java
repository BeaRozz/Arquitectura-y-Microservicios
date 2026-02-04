package com.modelo.services.modules.earings.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EaringResponse {
    
    private String message;
    private EaringDTO earing;

}

package com.modelo.services.modules.earings.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EaringListResponse {

    private String message;
    private List<EaringDTO> earings;
    
}

package com.modelo.services.modules.earings.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Document(collection = "earings")
public class Erings {
    
    @Id
    private String id;

    private String model;
    private String color;
    private Integer size;
    private String edition;
    private String material;

}

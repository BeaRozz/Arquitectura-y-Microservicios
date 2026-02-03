package com.modelo.services.modules.cows.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "cows")
public class Cows {
    
    @Id
    private String id;

    private String name;
    private String breed;
    private Integer age;
}

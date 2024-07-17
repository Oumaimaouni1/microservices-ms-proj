package com.esprit.ms_restaurant.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "restaurant")
public class Restaurant {
    @Id
    private String id;
    private String name;
    private String localisation;

}


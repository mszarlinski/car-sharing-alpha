package com.example.carsharing.rentals.domain.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
public class Rental {

    @Id
    private String id;

    private String userId;

    private String carId;

    public Rental(String userId, String carId) {
        this.userId = userId;
        this.carId = carId;
    }
}

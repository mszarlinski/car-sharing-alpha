package com.example.carsharing.billing.domain.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
@Getter
public class BillingEntry {

    @Id
    private String id;

    private String carId;

    private String userId;

    private BigDecimal price;

    public BillingEntry(String carId, String userId, BigDecimal price) {
        this.carId = carId;
        this.userId = userId;
        this.price = price;
    }
}

package com.example.carsharing.sharedkernel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.UUID;

@Value
public class NewRentalEvent {
    private UUID uuid;
    private String userId;
    private String carId;

    public NewRentalEvent(String userId, String carId) {
        this.uuid = UUID.randomUUID();
        this.userId = userId;
        this.carId = carId;
    }

    @JsonCreator
    private NewRentalEvent(
            @JsonProperty("uuid") UUID uuid,
            @JsonProperty("userId") String userId,
            @JsonProperty("carid") String carId) {
        this.uuid = uuid;
        this.userId = userId;
        this.carId = carId;
    }
}

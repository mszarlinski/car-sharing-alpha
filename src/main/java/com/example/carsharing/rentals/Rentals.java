package com.example.carsharing.rentals;

import com.example.carsharing.rentals.domain.model.NewRentalPublisher;
import com.example.carsharing.rentals.domain.model.Rental;
import com.example.carsharing.rentals.domain.model.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class Rentals {

    private final RentalRepository rentalRepository;

    private final NewRentalPublisher newRentalPublisher;

    public Mono<Rental> rentACar(String carId, String userId) {
        return rentalRepository.save(new Rental(userId, carId))
                .doOnSuccess(newRentalPublisher::rentalCreated);
    }
}

package com.example.carsharing.rentals.domain.model;

import org.springframework.data.repository.Repository;
import reactor.core.publisher.Mono;

public interface RentalRepository extends Repository<Rental, String> {
    Mono<Rental> save(Rental rental);
}

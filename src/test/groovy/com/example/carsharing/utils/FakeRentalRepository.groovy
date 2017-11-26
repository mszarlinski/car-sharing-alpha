package com.example.carsharing.utils

import com.example.carsharing.rentals.domain.model.Rental
import com.example.carsharing.rentals.domain.model.RentalRepository
import reactor.core.publisher.Mono


class FakeRentalRepository implements RentalRepository {
    @Override
    Mono<Rental> save(Rental rental) {
        return Mono.just(rental)
    }
}

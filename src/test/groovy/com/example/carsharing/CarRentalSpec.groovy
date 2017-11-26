package com.example.carsharing

import com.example.carsharing.rentals.Rentals
import com.example.carsharing.rentals.domain.model.NewRentalPublisher
import com.example.carsharing.rentals.domain.model.RentalRepository
import com.example.carsharing.utils.FakeRentalRepository
import spock.lang.Specification
import spock.lang.Subject

class CarRentalSpec extends Specification {

    static String CAR_ID = "12345"
    static String USER_ID = "987651"

    @Subject
    Rentals rentals

    NewRentalPublisher newRentalPublisher = Mock(NewRentalPublisher)

    RentalRepository rentalRepository = new FakeRentalRepository()

    def setup() {
        rentals = new Rentals(rentalRepository, newRentalPublisher) //TODO: create instance with Configuration
    }

    def "should notify about car rental"() {
        when:
            rentals.rentACar(CAR_ID, USER_ID).block()

        then:
            1 * newRentalPublisher.rentalCreated({ it.carId == CAR_ID && it.userId == USER_ID })
    }
}

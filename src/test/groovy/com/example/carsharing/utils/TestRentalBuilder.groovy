package com.example.carsharing.utils

import com.example.carsharing.rentals.domain.model.Rental


class TestRentalBuilder {

    static DEFAULT_PARAMS = [
            carId : 'carId',
            userId: 'userId'
    ]

    static Rental rental(Map params = [:]) {
        params = DEFAULT_PARAMS + params
        new Rental(params.userId, params.carId)
    }
}

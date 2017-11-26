package com.example.carsharing.utils

import com.example.carsharing.CarSharingDemoApplication
import com.example.carsharing.rentals.Rentals
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration
@SpringBootTest(
        classes = [CarSharingDemoApplication],
        webEnvironment = SpringBootTest.WebEnvironment.NONE
)
class IntegrationSpec extends Specification {

    static String CAR_ID = "1234"
    static String USER_ID = "5678"

    @Autowired
    Rentals rentals

    @Autowired
    MongoOperations mongoOperations
}

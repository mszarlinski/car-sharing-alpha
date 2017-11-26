package com.example.carsharing

import com.example.carsharing.billing.domain.model.BillingEntry
import com.example.carsharing.rentals.domain.model.Rental
import com.example.carsharing.utils.EmbeddedRabbitMQAbility
import com.example.carsharing.utils.IntegrationSpec
import org.springframework.data.mongodb.core.query.Query

class CarSharingAcceptanceSpec extends IntegrationSpec implements EmbeddedRabbitMQAbility {

    def "Sample acceptance test"() {
        when:
            rentals.rentACar(CAR_ID, USER_ID).block()
            sleep(1000) //FIXME: flapping test - but I don't want to add Awaitility at the moment

        then:
            Rental saved = mongoOperations.findOne(new Query(), Rental)
            saved.getCarId() == CAR_ID

            BillingEntry billingLine = mongoOperations.findOne(new Query(), BillingEntry)
            billingLine.getCarId() == CAR_ID
    }
}

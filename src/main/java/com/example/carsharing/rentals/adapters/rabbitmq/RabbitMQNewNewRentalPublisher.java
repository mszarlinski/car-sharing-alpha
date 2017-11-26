package com.example.carsharing.rentals.adapters.rabbitmq;

import com.example.carsharing.infrastructure.json.JsonConverter;
import com.example.carsharing.rentals.domain.model.NewRentalPublisher;
import com.example.carsharing.rentals.domain.model.Rental;
import com.example.carsharing.sharedkernel.NewRentalEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.OutboundMessage;
import reactor.rabbitmq.QueueSpecification;
import reactor.rabbitmq.ReactorRabbitMq;
import reactor.rabbitmq.Sender;

import javax.annotation.PreDestroy;

import static com.example.carsharing.infrastructure.rabbitmq.RabbitMQProperties.CAR_SHARING_QUEUE;

@Component
@Slf4j
class RabbitMQNewNewRentalPublisher implements NewRentalPublisher {

    private final Sender sender = ReactorRabbitMq.createSender();
    private final JsonConverter jsonConverter;

    RabbitMQNewNewRentalPublisher(JsonConverter jsonConverter) {
        this.jsonConverter = jsonConverter;
        sender.declareQueue(QueueSpecification.queue(CAR_SHARING_QUEUE)).block();
    }

    @PreDestroy
    void cleaup() {
        sender.close();
    }

    @Override
    public void rentalCreated(Rental rental) {
        NewRentalEvent event = new NewRentalEvent(rental.getUserId(), rental.getCarId());

        sender.sendWithPublishConfirms(asMessage(event))
                .doOnError(e -> log.error("Failed to send message", e))
                .subscribe(m -> log.info("Message has been sent successfully"));
    }

    private Mono<OutboundMessage> asMessage(NewRentalEvent event) {
        return Mono.just(new OutboundMessage("", CAR_SHARING_QUEUE, jsonConverter.toJson(event)));
    }
}

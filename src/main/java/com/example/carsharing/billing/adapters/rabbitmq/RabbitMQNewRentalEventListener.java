package com.example.carsharing.billing.adapters.rabbitmq;

import com.example.carsharing.billing.Billing;
import com.example.carsharing.infrastructure.json.JsonConverter;
import com.example.carsharing.sharedkernel.NewRentalEvent;
import com.rabbitmq.client.Delivery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.rabbitmq.QueueSpecification;
import reactor.rabbitmq.ReactorRabbitMq;
import reactor.rabbitmq.Receiver;
import reactor.rabbitmq.Sender;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static com.example.carsharing.infrastructure.rabbitmq.RabbitMQProperties.CAR_SHARING_QUEUE;

@Component
@Slf4j
class RabbitMQNewRentalEventListener {

    private final Billing billing;

    private final JsonConverter jsonConverter;

    private final Receiver receiver = ReactorRabbitMq.createReceiver();

    private final Sender sender = ReactorRabbitMq.createSender();

    RabbitMQNewRentalEventListener(Billing billing, JsonConverter jsonConverter) {
        this.billing = billing;
        this.jsonConverter = jsonConverter;
    }

    @PostConstruct
    void init() {
        Flux<Delivery> deliveries = receiver.consumeNoAck(CAR_SHARING_QUEUE);

        sender.declareQueue(QueueSpecification.queue(CAR_SHARING_QUEUE))
                .thenMany(deliveries)
                .subscribe(this::processDelivery);
    }

    @PreDestroy
    void cleaup() {
        sender.close();
        receiver.close();
    }

    private void processDelivery(Delivery delivery) {
        log.info("Received delivery from queue");
        billing.onNewRental(jsonConverter.fromJson(delivery.getBody(), NewRentalEvent.class));
    }
}

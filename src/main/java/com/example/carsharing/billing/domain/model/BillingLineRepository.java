package com.example.carsharing.billing.domain.model;

import org.springframework.data.repository.Repository;
import reactor.core.publisher.Mono;

public interface BillingLineRepository extends Repository<BillingEntry, String> {

    Mono<BillingEntry> save(BillingEntry billingEntry);
}

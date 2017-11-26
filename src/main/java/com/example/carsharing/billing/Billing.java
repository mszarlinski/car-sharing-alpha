package com.example.carsharing.billing;

import com.example.carsharing.billing.domain.model.BillingEntry;
import com.example.carsharing.billing.domain.model.BillingLineRepository;
import com.example.carsharing.sharedkernel.NewRentalEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class Billing {

    private final BillingLineRepository billingLineRepository;

    public void onNewRental(NewRentalEvent event) {
        log.info("New rental created!");
        billingLineRepository.save(new BillingEntry(event.getCarId(), event.getUserId(), BigDecimal.TEN))
                .subscribe(bl -> log.info("Billing has been saved"));
    }
}

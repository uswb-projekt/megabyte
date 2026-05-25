package edu.prz.delivery.payments.domain.payment;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @GetMapping
    public Map<String, String> test() {

        return Map.of(
                "status", "OK",
                "message", "payment-ok"
        );
    }

    @PostMapping
    public Map<String, String> createPayment(
            @RequestBody String body
    ) {

        return Map.of(
                "status", "OK",
                "message", "Payment created"
        );
    }
}
package edu.prz.delivery.orders.domain.foodorder;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/food-orders")
public class FoodOrderController {

    @GetMapping
    public Map<String, String> test() {

        return Map.of(
                "status", "OK",
                "message", "food-order-ok"
        );
    }

    @PostMapping
    public Map<String, String> createOrder(
            @RequestBody String body
    ) {

        return Map.of(
                "status", "OK",
                "message", "Order created"
        );
    }
}
package edu.prz.delivery.orders.domain.foodorder;

import edu.prz.delivery.payments.domain.payment.PaymentMethod;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food-orders")
@RequiredArgsConstructor
public class FoodOrderController {

  private final FoodOrderService service;

  @GetMapping
  public List<FoodOrder> getAll() {
    return service.list(Pageable.unpaged()).getContent();
  }

  @GetMapping("/{id}")
  public ResponseEntity<FoodOrder> getById(@PathVariable Long id) {
    return service.get(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<FoodOrder> create(@RequestBody FoodOrder foodOrder) {
    FoodOrder saved = service.placeOrder(foodOrder);
    return ResponseEntity.status(HttpStatus.CREATED).body(saved);
  }

  @PutMapping("/{id}")
  public ResponseEntity<FoodOrder> update(@PathVariable Long id, @RequestBody FoodOrder foodOrder) {
    return service.get(id)
        .map(existing -> {
          foodOrder.setId(id);
          foodOrder.setVersion(existing.getVersion());
          FoodOrder updated = service.update(foodOrder);
          return ResponseEntity.ok(updated);
        })
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    if (service.get(id).isPresent()) {
      service.delete(id);
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping("/{id}/pay")
  public ResponseEntity<FoodOrder> pay(
      @PathVariable Long id,
      @RequestParam PaymentMethod method,
      @RequestParam BigDecimal amount
  ) {
    try {
      FoodOrder paidOrder = service.payOrder(id, method, amount);
      return ResponseEntity.ok(paidOrder);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/{id}/status")
  public ResponseEntity<Map<String, Object>> getStatus(@PathVariable Long id) {
    return service.get(id)
        .map(order -> ResponseEntity.ok(Map.<String, Object>of(
            "id", order.getId(),
            "status", order.getStatus(),
            "estimatedDeliveryTime", order.getEstimatedDeliveryTime() != null ? order.getEstimatedDeliveryTime().toString() : "N/A",
            "deliveryTime", order.getDeliveryTime() != null ? order.getDeliveryTime().toString() : "N/A",
            "deliveryAddress", order.getDeliveryAddress() != null ? order.getDeliveryAddress() : "N/A"
        )))
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/customer/{customerId}")
  public List<FoodOrder> getOrdersByCustomer(@PathVariable Long customerId) {
    return service.getOrdersByCustomerId(customerId);
  }

  @PostMapping("/{id}/pickup")
  public ResponseEntity<FoodOrder> pickup(
      @PathVariable Long id,
      @RequestParam Long courierId
  ) {
    try {
      FoodOrder order = service.pickupOrder(id, courierId);
      return ResponseEntity.ok(order);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/{id}/deliver")
  public ResponseEntity<FoodOrder> deliver(@PathVariable Long id) {
    try {
      FoodOrder order = service.deliverOrder(id);
      return ResponseEntity.ok(order);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
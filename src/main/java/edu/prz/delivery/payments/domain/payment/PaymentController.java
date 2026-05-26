package edu.prz.delivery.payments.domain.payment;

import edu.prz.delivery.foundation.exceptions.ResourceNotFoundException;
import edu.prz.delivery.orders.domain.foodorder.FoodOrder;
import edu.prz.delivery.orders.domain.foodorder.FoodOrderService;
import edu.prz.delivery.payments.domain.payment.dto.CreatePaymentRequest;
import edu.prz.delivery.payments.domain.payment.dto.PaymentResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

  private final PaymentService service;
  private final FoodOrderService orderService;

  @GetMapping
  @Operation(
      summary = "Pobieranie listy płatności",
      description = "Pobiera listę wszystkich płatności zarejestrowanych w systemie."
  )
  public List<PaymentResponse> getAll() {
    return service.list(Pageable.unpaged()).getContent().stream()
        .map(this::mapToResponse)
        .toList();
  }

  @GetMapping("/{id}")
  @Operation(
      summary = "Pobieranie szczegółów płatności po ID",
      description = "Pobiera szczegółowe dane płatności o podanym ID."
  )
  public ResponseEntity<PaymentResponse> getById(@PathVariable Long id) {
    Payment payment = service.get(id)
        .orElseThrow(() -> new ResourceNotFoundException("Payment not found with ID " + id));
    return ResponseEntity.ok(mapToResponse(payment));
  }

  @GetMapping("/order/{orderId}")
  @Operation(
      summary = "Pobieranie płatności po ID zamówienia",
      description = "Pobiera szczegóły płatności przypisanej do konkretnego zamówienia."
  )
  public ResponseEntity<PaymentResponse> getByOrderId(@PathVariable Long orderId) {
    Payment payment = service.getByOrderId(orderId)
        .orElseThrow(() -> new ResourceNotFoundException("Payment not found for order ID " + orderId));
    return ResponseEntity.ok(mapToResponse(payment));
  }

  @PostMapping
  @Operation(
      summary = "Utworzenie nowej płatności",
      description = "Rejestruje nową płatność powiązaną z zamówieniem w stanie PENDING."
  )
  public ResponseEntity<PaymentResponse> create(@Valid @RequestBody CreatePaymentRequest request) {
    FoodOrder order = orderService.get(request.getOrderId())
        .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID " + request.getOrderId()));
    
    Payment payment = new Payment();
    payment.setAmount(request.getAmount());
    payment.setMethod(request.getMethod());
    payment.setOrder(order);
    payment.setStatus(request.getMethod() == PaymentMethod.ONLINE ? PaymentStatus.COMPLETED : PaymentStatus.PENDING);
    
    Payment saved = service.update(payment);
    return ResponseEntity.status(HttpStatus.CREATED).body(mapToResponse(saved));
  }

  @PutMapping("/{id}")
  @Operation(
      summary = "Aktualizacja danych płatności",
      description = "Aktualizuje dane kwoty, metody i zamówienia dla płatności o podanym ID."
  )
  public ResponseEntity<PaymentResponse> update(
      @PathVariable Long id,
      @Valid @RequestBody CreatePaymentRequest request
  ) {
    Payment existing = service.get(id)
        .orElseThrow(() -> new ResourceNotFoundException("Payment not found with ID " + id));
    FoodOrder order = orderService.get(request.getOrderId())
        .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID " + request.getOrderId()));
    
    Payment payment = new Payment();
    payment.setId(id);
    payment.setVersion(existing.getVersion());
    payment.setAmount(request.getAmount());
    payment.setMethod(request.getMethod());
    payment.setOrder(order);
    payment.setStatus(existing.getStatus());
    
    Payment updated = service.update(payment);
    return ResponseEntity.ok(mapToResponse(updated));
  }

  @PatchMapping("/{id}/status")
  @Operation(
      summary = "Aktualizacja statusu płatności",
      description = "Pozwala na bezpośrednią zmianę statusu płatności w ramach testów, weryfikując poprawność przejścia stanów."
  )
  public ResponseEntity<PaymentResponse> patchStatus(
      @PathVariable Long id,
      @RequestParam PaymentStatus status
  ) {
    Payment payment = service.patchStatus(id, status);
    return ResponseEntity.ok(mapToResponse(payment));
  }

  @DeleteMapping("/{id}")
  @Operation(
      summary = "Usunięcie płatności",
      description = "Usuwa płatność o podanym ID z bazy danych."
  )
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    Payment existing = service.get(id)
        .orElseThrow(() -> new ResourceNotFoundException("Payment not found with ID " + id));
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  private PaymentResponse mapToResponse(Payment payment) {
    if (payment == null) return null;
    return PaymentResponse.builder()
        .id(payment.getId())
        .version(payment.getVersion())
        .amount(payment.getAmount())
        .status(payment.getStatus())
        .method(payment.getMethod())
        .build();
  }
}
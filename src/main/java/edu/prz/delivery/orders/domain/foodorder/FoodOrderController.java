package edu.prz.delivery.orders.domain.foodorder;

import edu.prz.delivery.foundation.exceptions.ResourceNotFoundException;
import edu.prz.delivery.orders.domain.foodorder.dto.CreateFoodOrderRequest;
import edu.prz.delivery.orders.domain.foodorder.dto.FoodOrderResponse;
import edu.prz.delivery.orders.domain.foodorder.dto.OrderItemDto;
import edu.prz.delivery.payments.domain.payment.PaymentMethod;
import edu.prz.delivery.payments.domain.payment.dto.PaymentResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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
import org.springframework.web.bind.annotation.PatchMapping;
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
  @Operation(
      summary = "Pobieranie listy zamówień",
      description = "Pobiera listę wszystkich złożonych zamówień w systemie."
  )
  public List<FoodOrderResponse> getAll() {
    return service.list(Pageable.unpaged()).getContent().stream()
        .map(this::mapToResponse)
        .toList();
  }

  @GetMapping("/{id}")
  @Operation(
      summary = "Pobieranie szczegółów zamówienia po ID",
      description = "Pobiera szczegółowe dane zamówienia o podanym ID."
  )
  public ResponseEntity<FoodOrderResponse> getById(@PathVariable Long id) {
    FoodOrder order = service.get(id)
        .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID " + id));
    return ResponseEntity.ok(mapToResponse(order));
  }

  @PostMapping
  @Operation(
      summary = "Złożenie nowego zamówienia",
      description = "Tworzy nowe zamówienie jedzenia (agregat FoodOrder) w stanie PENDING. Wymaga klienta, restauracji, adresu i listy dań."
  )
  public ResponseEntity<FoodOrderResponse> create(@Valid @RequestBody CreateFoodOrderRequest request) {
    FoodOrder order = mapToEntity(request);
    FoodOrder saved = service.placeOrder(order);
    return ResponseEntity.status(HttpStatus.CREATED).body(mapToResponse(saved));
  }

  @PutMapping("/{id}")
  @Operation(
      summary = "Aktualizacja danych zamówienia",
      description = "Aktualizuje dane klienta, restauracji, adresu i pozycji w zamówieniu o podanym ID."
  )
  public ResponseEntity<FoodOrderResponse> update(
      @PathVariable Long id,
      @Valid @RequestBody CreateFoodOrderRequest request
  ) {
    FoodOrder existing = service.get(id)
        .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID " + id));
    FoodOrder order = mapToEntity(request);
    order.setId(id);
    order.setVersion(existing.getVersion());
    FoodOrder updated = service.update(order);
    return ResponseEntity.ok(mapToResponse(updated));
  }

  @DeleteMapping("/{id}")
  @Operation(
      summary = "Usunięcie zamówienia",
      description = "Usuwa zamówienie o podanym ID ze struktur bazy danych."
  )
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    FoodOrder existing = service.get(id)
        .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID " + id));
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/{id}/pay")
  @Operation(
      summary = "Opłacenie zamówienia",
      description = "Obsługuje płatność za zamówienie o statusie PENDING. Zmienia status zamówienia na ACCEPTED."
  )
  public ResponseEntity<FoodOrderResponse> pay(
      @PathVariable Long id,
      @RequestParam PaymentMethod method,
      @RequestParam BigDecimal amount
  ) {
    FoodOrder paidOrder = service.payOrder(id, method, amount);
    return ResponseEntity.ok(mapToResponse(paidOrder));
  }

  @PatchMapping("/{id}/status")
  @Operation(
      summary = "Aktualizacja statusu zamówienia",
      description = "Pozwala bezpośrednio zmienić status zamówienia (np. do testowania Bruno/Bruno), weryfikując poprawność przejścia stanów w maszynie stanów."
  )
  public ResponseEntity<FoodOrderResponse> patchStatus(
      @PathVariable Long id,
      @RequestParam OrderStatus status
  ) {
    FoodOrder order = service.patchStatus(id, status);
    return ResponseEntity.ok(mapToResponse(order));
  }

  @GetMapping("/{id}/status")
  @Operation(
      summary = "Pobieranie statusu i czasów dostawy",
      description = "Pobiera krótki podgląd statusu, szacowanego czasu dostawy oraz rzeczywistego czasu dostawy dla wybranego zamówienia."
  )
  public ResponseEntity<Map<String, Object>> getStatus(@PathVariable Long id) {
    FoodOrder order = service.get(id)
        .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID " + id));
    return ResponseEntity.ok(Map.<String, Object>of(
        "id", order.getId(),
        "status", order.getStatus(),
        "estimatedDeliveryTime", order.getEstimatedDeliveryTime() != null ? order.getEstimatedDeliveryTime().toString() : "N/A",
        "deliveryTime", order.getDeliveryTime() != null ? order.getDeliveryTime().toString() : "N/A",
        "deliveryAddress", order.getDeliveryAddress() != null ? order.getDeliveryAddress() : "N/A"
    ));
  }

  @GetMapping("/customer/{customerId}")
  @Operation(
      summary = "Pobieranie zamówień klienta",
      description = "Pobiera listę wszystkich zamówień złożonych przez danego klienta o podanym customerId."
  )
  public List<FoodOrderResponse> getOrdersByCustomer(@PathVariable Long customerId) {
    return service.getOrdersByCustomerId(customerId).stream()
        .map(this::mapToResponse)
        .toList();
  }

  @PostMapping("/{id}/pickup")
  @Operation(
      summary = "Odebranie zamówienia przez kuriera",
      description = "Oznacza zamówienie jako odebrane do dostawy (zmienia status na IN_DELIVERY) i przypisuje kuriera o podanym ID."
  )
  public ResponseEntity<FoodOrderResponse> pickup(
      @PathVariable Long id,
      @RequestParam Long courierId
  ) {
    FoodOrder order = service.pickupOrder(id, courierId);
    return ResponseEntity.ok(mapToResponse(order));
  }

  @PostMapping("/{id}/deliver")
  @Operation(
      summary = "Dostarczenie zamówienia przez kuriera",
      description = "Oznacza zamówienie jako pomyślnie dostarczone do klienta (zmienia status na DELIVERED) i oznacza powiązaną płatność jako COMPLETED."
  )
  public ResponseEntity<FoodOrderResponse> deliver(@PathVariable Long id) {
    FoodOrder order = service.deliverOrder(id);
    return ResponseEntity.ok(mapToResponse(order));
  }

  private FoodOrderResponse mapToResponse(FoodOrder order) {
    if (order == null) return null;
    return FoodOrderResponse.builder()
        .id(order.getId())
        .version(order.getVersion())
        .description(order.getDescription())
        .orderTime(order.getOrderTime())
        .estimatedDeliveryTime(order.getEstimatedDeliveryTime())
        .deliveryTime(order.getDeliveryTime())
        .status(order.getStatus())
        .customerId(order.getCustomerId())
        .restaurantId(order.getRestaurantId())
        .courierId(order.getCourierId())
        .deliveryAddress(order.getDeliveryAddress())
        .items(order.getItems().stream()
            .map(this::mapToItemDto)
            .toList())
        .payment(mapToPaymentResponse(order.getPayment()))
        .build();
  }

  private OrderItemDto mapToItemDto(OrderItem item) {
    if (item == null) return null;
    return OrderItemDto.builder()
        .id(item.getId())
        .productId(item.getProductId())
        .productName(item.getProductName())
        .quantity(item.getQuantity())
        .price(item.getPrice())
        .notes(item.getNotes())
        .build();
  }

  private PaymentResponse mapToPaymentResponse(edu.prz.delivery.payments.domain.payment.Payment payment) {
    if (payment == null) return null;
    return PaymentResponse.builder()
        .id(payment.getId())
        .version(payment.getVersion())
        .amount(payment.getAmount())
        .status(payment.getStatus())
        .method(payment.getMethod())
        .build();
  }

  private FoodOrder mapToEntity(CreateFoodOrderRequest request) {
    if (request == null) return null;
    FoodOrder order = new FoodOrder();
    order.setDescription(request.getDescription());
    order.setCustomerId(request.getCustomerId());
    order.setRestaurantId(request.getRestaurantId());
    order.setDeliveryAddress(request.getDeliveryAddress());
    if (request.getItems() != null) {
      order.setItems(request.getItems().stream()
          .map(this::mapToItemEntity)
          .toList());
    }
    return order;
  }

  private OrderItem mapToItemEntity(OrderItemDto dto) {
    if (dto == null) return null;
    OrderItem item = new OrderItem();
    item.setProductId(dto.getProductId());
    item.setProductName(dto.getProductName());
    item.setQuantity(dto.getQuantity());
    item.setPrice(dto.getPrice());
    item.setNotes(dto.getNotes());
    return item;
  }
}
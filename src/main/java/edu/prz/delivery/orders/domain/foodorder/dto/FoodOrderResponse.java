package edu.prz.delivery.orders.domain.foodorder.dto;

import edu.prz.delivery.orders.domain.foodorder.OrderStatus;
import edu.prz.delivery.payments.domain.payment.dto.PaymentResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodOrderResponse {

  private Long id;
  private int version;
  private String description;
  private LocalDateTime orderTime;
  private LocalDateTime estimatedDeliveryTime;
  private LocalDateTime deliveryTime;
  private OrderStatus status;
  private Long customerId;
  private Long restaurantId;
  private Long courierId;
  private String deliveryAddress;

  @Builder.Default
  private List<OrderItemDto> items = new ArrayList<>();

  private PaymentResponse payment;
}

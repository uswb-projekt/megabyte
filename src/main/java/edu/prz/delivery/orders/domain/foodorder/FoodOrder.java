package edu.prz.delivery.orders.domain.foodorder;

import edu.prz.delivery.foundation.domain.BaseEntity;
import edu.prz.delivery.payments.domain.payment.Payment;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "food_orders")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class FoodOrder extends BaseEntity {

  private String description;
  private LocalDateTime orderTime;
  private LocalDateTime estimatedDeliveryTime;
  private LocalDateTime deliveryTime;

  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  private Long customerId;
  private Long restaurantId;
  private Long courierId;
  private String deliveryAddress;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  @JoinColumn(name = "order_id")
  private List<OrderItem> items = new ArrayList<>();

  @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  private Payment payment;
}

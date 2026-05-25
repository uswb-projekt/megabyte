package edu.prz.delivery.payments.domain.payment;

import edu.prz.delivery.foundation.domain.BaseEntity;
import edu.prz.delivery.orders.domain.foodorder.FoodOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "payments")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Payment extends BaseEntity {

  private BigDecimal amount;

  @Enumerated(EnumType.STRING)
  private PaymentStatus status;

  @Enumerated(EnumType.STRING)
  private PaymentMethod method;

  @OneToOne
  @JoinColumn(name = "order_id")
  private FoodOrder order;
}

package edu.prz.delivery.orders.domain.foodorder;

import edu.prz.delivery.foundation.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "order_items")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class OrderItem extends BaseEntity {

  private Long productId;
  private String productName;
  private int quantity;
  private BigDecimal price;
  private String notes;
}

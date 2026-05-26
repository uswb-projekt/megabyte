package edu.prz.delivery.restaurants.domain.restaurant;

import edu.prz.delivery.foundation.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "product_variants")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class ProductVariant extends BaseEntity {

  private String name;
  private BigDecimal price;
}

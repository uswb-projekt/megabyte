package edu.prz.delivery.orders.domain.foodorder.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

  @Schema(example = "1")
  private Long id;

  @NotNull(message = "ID produktu jest wymagane")
  @Schema(example = "1")
  private Long productId;

  @NotBlank(message = "Nazwa produktu nie może być pusta")
  @Schema(example = "Pizza Margherita")
  private String productName;

  @Positive(message = "Ilość musi być dodatnia")
  @Schema(example = "2")
  private int quantity;

  @NotNull(message = "Cena jest wymagana")
  @Positive(message = "Cena musi być dodatnia")
  @Schema(example = "36.00")
  private BigDecimal price;

  @Schema(example = "Bez oregano")
  private String notes;
}

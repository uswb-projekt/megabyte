package edu.prz.delivery.orders.domain.foodorder.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class CreateFoodOrderRequest {

  @Schema(example = "Prośba o podwójny ser na marghericie.")
  private String description;

  @NotNull(message = "ID klienta jest wymagane")
  @Schema(example = "101")
  private Long customerId;

  @NotNull(message = "ID restauracji jest wymagane")
  @Schema(example = "1")
  private Long restaurantId;

  @NotBlank(message = "Adres dostawy nie może być pusty")
  @Schema(example = "ul. Chopina 15, Rzeszów")
  private String deliveryAddress;

  @NotEmpty(message = "Zamówienie musi zawierać co najmniej jedną pozycję")
  @Valid
  @Builder.Default
  private List<OrderItemDto> items = new ArrayList<>();
}

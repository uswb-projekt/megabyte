package edu.prz.delivery.restaurants.domain.restaurant.dto;

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
public class ProductVariantDto {

  @Schema(example = "1")
  private Long id;

  @NotBlank(message = "Nazwa wariantu nie może być pusta")
  @Schema(example = "Rodzinna (45cm)")
  private String name;

  @NotNull(message = "Cena wariantu jest wymagana")
  @Positive(message = "Cena wariantu musi być dodatnia")
  @Schema(example = "46.00")
  private BigDecimal price;
}

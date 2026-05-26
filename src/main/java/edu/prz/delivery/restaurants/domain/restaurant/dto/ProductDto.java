package edu.prz.delivery.restaurants.domain.restaurant.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
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
public class ProductDto {

  @Schema(example = "1")
  private Long id;

  @NotBlank(message = "Nazwa produktu nie może być pusta")
  @Schema(example = "Pizza Margherita")
  private String name;

  @Schema(example = "Klasyczna pizza neapolitańska z sosem pomidorowym i mozzarellą")
  private String description;

  @Schema(example = "Pizza")
  private String category;

  @NotNull(message = "Cena produktu jest wymagana")
  @Positive(message = "Cena produktu musi być dodatnia")
  @Schema(example = "36.00")
  private BigDecimal price;

  @Schema(example = "true")
  private boolean available;

  @Builder.Default
  @Valid
  private List<ProductVariantDto> variants = new ArrayList<>();
}

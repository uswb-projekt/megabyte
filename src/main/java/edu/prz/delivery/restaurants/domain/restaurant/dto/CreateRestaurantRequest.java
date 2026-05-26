package edu.prz.delivery.restaurants.domain.restaurant.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRestaurantRequest {

  @NotBlank(message = "Nazwa restauracji nie może być pusta")
  @Schema(example = "Pizzeria Bella Italia")
  private String name;

  @NotBlank(message = "Adres restauracji nie może być pusty")
  @Schema(example = "Krakowskie Przedmiescie 12, Warszawa")
  private String address;

  @NotBlank(message = "Godziny otwarcia są wymagane")
  @Schema(example = "12:00 - 23:00")
  private String openingHours;

  @NotBlank(message = "Dane kontaktowe są wymagane")
  @Schema(example = "22-123-45-67")
  private String contactInfo;
}

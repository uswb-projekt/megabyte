package edu.prz.delivery.restaurants.domain.restaurant.dto;

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
public class RestaurantResponse {

  private Long id;
  private int version;
  private String name;
  private String address;
  private String openingHours;
  private String contactInfo;

  @Builder.Default
  private List<ProductDto> products = new ArrayList<>();
}

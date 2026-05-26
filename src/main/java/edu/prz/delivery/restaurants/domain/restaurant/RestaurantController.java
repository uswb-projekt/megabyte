package edu.prz.delivery.restaurants.domain.restaurant;

import edu.prz.delivery.foundation.exceptions.ResourceNotFoundException;
import edu.prz.delivery.restaurants.domain.restaurant.dto.CreateRestaurantRequest;
import edu.prz.delivery.restaurants.domain.restaurant.dto.ProductDto;
import edu.prz.delivery.restaurants.domain.restaurant.dto.ProductVariantDto;
import edu.prz.delivery.restaurants.domain.restaurant.dto.RestaurantResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

  private final RestaurantService service;

  @GetMapping
  @Operation(
      summary = "Pobieranie listy restauracji",
      description = "Pobiera listę wszystkich zarejestrowanych restauracji w systemie."
  )
  public List<RestaurantResponse> getAll() {
    return service.list(Pageable.unpaged()).getContent().stream()
        .map(this::mapToResponse)
        .toList();
  }

  @GetMapping("/{id}")
  @Operation(
      summary = "Pobieranie szczegółów restauracji po ID",
      description = "Pobiera szczegółowe dane restauracji o podanym identyfikatorze ID."
  )
  public ResponseEntity<RestaurantResponse> getById(@PathVariable Long id) {
    Restaurant restaurant = service.get(id)
        .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with ID " + id));
    return ResponseEntity.ok(mapToResponse(restaurant));
  }

  @GetMapping("/{id}/products")
  @Operation(
      summary = "Pobieranie menu restauracji",
      description = "Pobiera listę wszystkich produktów (z ich wariantami) przypisanych do danej restauracji."
  )
  public ResponseEntity<List<ProductDto>> getProducts(@PathVariable Long id) {
    Restaurant restaurant = service.get(id)
        .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with ID " + id));
    List<ProductDto> products = restaurant.getProducts().stream()
        .map(this::mapToProductDto)
        .toList();
    return ResponseEntity.ok(products);
  }

  @PostMapping
  @Operation(
      summary = "Tworzenie nowej restauracji",
      description = "Tworzy nowy agregat Restauracji. Wymaga podania nazwy, adresu, godzin otwarcia oraz danych kontaktowych."
  )
  public ResponseEntity<RestaurantResponse> create(@Valid @RequestBody CreateRestaurantRequest request) {
    Restaurant restaurant = mapToEntity(request);
    Restaurant saved = service.update(restaurant);
    return ResponseEntity.status(HttpStatus.CREATED).body(mapToResponse(saved));
  }

  @PutMapping("/{id}")
  @Operation(
      summary = "Aktualizacja danych restauracji",
      description = "Aktualizuje podstawowe dane adresowe i kontaktowe restauracji o podanym ID."
  )
  public ResponseEntity<RestaurantResponse> update(
      @PathVariable Long id,
      @Valid @RequestBody CreateRestaurantRequest request
  ) {
    Restaurant existing = service.get(id)
        .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with ID " + id));
    Restaurant restaurant = mapToEntity(request);
    restaurant.setId(id);
    restaurant.setVersion(existing.getVersion());
    Restaurant updated = service.update(restaurant);
    return ResponseEntity.ok(mapToResponse(updated));
  }

  @DeleteMapping("/{id}")
  @Operation(
      summary = "Usunięcie restauracji",
      description = "Usuwa restaurację o podanym ID ze struktur bazy danych."
  )
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    Restaurant existing = service.get(id)
        .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with ID " + id));
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/{id}/products")
  @Operation(
      summary = "Dodawanie produktu do menu",
      description = "Dodaje nowy produkt (z opcjonalnymi wariantami cenowymi) do menu danej restauracji."
  )
  public ResponseEntity<RestaurantResponse> addProduct(
      @PathVariable Long id,
      @Valid @RequestBody ProductDto productDto
  ) {
    Product product = mapToProductEntity(productDto);
    Restaurant updated = service.addProduct(id, product);
    return ResponseEntity.ok(mapToResponse(updated));
  }

  @DeleteMapping("/{id}/products/{productId}")
  @Operation(
      summary = "Usuwanie produktu z menu",
      description = "Usuwa wybrany produkt z menu restauracji o podanym ID."
  )
  public ResponseEntity<RestaurantResponse> removeProduct(
      @PathVariable Long id,
      @PathVariable Long productId
  ) {
    Restaurant updated = service.removeProduct(id, productId);
    return ResponseEntity.ok(mapToResponse(updated));
  }

  private RestaurantResponse mapToResponse(Restaurant restaurant) {
    if (restaurant == null) return null;
    return RestaurantResponse.builder()
        .id(restaurant.getId())
        .version(restaurant.getVersion())
        .name(restaurant.getName())
        .address(restaurant.getAddress())
        .openingHours(restaurant.getOpeningHours())
        .contactInfo(restaurant.getContactInfo())
        .products(restaurant.getProducts().stream()
            .map(this::mapToProductDto)
            .toList())
        .build();
  }

  private ProductDto mapToProductDto(Product product) {
    if (product == null) return null;
    return ProductDto.builder()
        .id(product.getId())
        .name(product.getName())
        .description(product.getDescription())
        .category(product.getCategory())
        .price(product.getPrice())
        .available(product.isAvailable())
        .variants(product.getVariants().stream()
            .map(this::mapToVariantDto)
            .toList())
        .build();
  }

  private ProductVariantDto mapToVariantDto(ProductVariant variant) {
    if (variant == null) return null;
    return ProductVariantDto.builder()
        .id(variant.getId())
        .name(variant.getName())
        .price(variant.getPrice())
        .build();
  }

  private Restaurant mapToEntity(CreateRestaurantRequest request) {
    if (request == null) return null;
    Restaurant restaurant = new Restaurant();
    restaurant.setName(request.getName());
    restaurant.setAddress(request.getAddress());
    restaurant.setOpeningHours(request.getOpeningHours());
    restaurant.setContactInfo(request.getContactInfo());
    return restaurant;
  }

  private Product mapToProductEntity(ProductDto dto) {
    if (dto == null) return null;
    Product product = new Product();
    product.setName(dto.getName());
    product.setDescription(dto.getDescription());
    product.setCategory(dto.getCategory());
    product.setPrice(dto.getPrice());
    product.setAvailable(dto.isAvailable());
    if (dto.getVariants() != null) {
      product.setVariants(dto.getVariants().stream()
          .map(this::mapToVariantEntity)
          .toList());
    }
    return product;
  }

  private ProductVariant mapToVariantEntity(ProductVariantDto dto) {
    if (dto == null) return null;
    ProductVariant variant = new ProductVariant();
    variant.setName(dto.getName());
    variant.setPrice(dto.getPrice());
    return variant;
  }
}

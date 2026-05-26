package edu.prz.delivery.restaurants.domain.restaurant;

import edu.prz.delivery.foundation.exceptions.ResourceNotFoundException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RestaurantService {

  private final RestaurantRepository repository;

  public Optional<Restaurant> get(Long id) {
    return repository.findById(id);
  }

  public Restaurant update(Restaurant entity) {
    return repository.save(entity);
  }

  public void delete(Long id) {
    repository.deleteById(id);
  }

  public Page<Restaurant> list(Pageable pageable) {
    return repository.findAll(pageable);
  }

  public Page<Restaurant> list(Pageable pageable, Specification<Restaurant> filter) {
    return repository.findAll(filter, pageable);
  }

  public int count() {
    return (int) repository.count();
  }

  @Transactional
  public Restaurant addProduct(Long restaurantId, Product product) {
    Restaurant restaurant = repository.findById(restaurantId)
        .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with ID " + restaurantId));
    restaurant.getProducts().add(product);
    return repository.save(restaurant);
  }

  @Transactional
  public Restaurant removeProduct(Long restaurantId, Long productId) {
    Restaurant restaurant = repository.findById(restaurantId)
        .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with ID " + restaurantId));
    boolean removed = restaurant.getProducts().removeIf(product -> product.getId() != null && product.getId().equals(productId));
    if (!removed) {
      throw new ResourceNotFoundException("Product not found with ID " + productId + " in restaurant " + restaurantId);
    }
    return repository.save(restaurant);
  }
}

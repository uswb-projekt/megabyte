vvvvvvvvvvvpackage edu.prz.delivery.orders.domain.foodorder;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FoodOrderService {

  private final FoodOrderRepository repository;

  public Optional<FoodOrder> get(Long id) {
    return repository.findById(id);
  }

  public FoodOrder update(FoodOrder entity) {
    return repository.save(entity);
  }

  public void delete(Long id) {
    repository.deleteById(id);
  }

  public Page<FoodOrder> list(Pageable pageable) {
    return repository.findAll(pageable);
  }

  public Page<FoodOrder> list(Pageable pageable, Specification<FoodOrder> filter) {
    return repository.findAll(filter, pageable);
  }

  public int count() {
    return (int) repository.count();
  }
}

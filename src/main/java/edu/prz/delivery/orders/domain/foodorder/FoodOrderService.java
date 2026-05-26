package edu.prz.delivery.orders.domain.foodorder;

import edu.prz.delivery.payments.domain.payment.Payment;
import edu.prz.delivery.payments.domain.payment.PaymentMethod;
import edu.prz.delivery.payments.domain.payment.PaymentStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  @Transactional
  public FoodOrder placeOrder(FoodOrder order) {
    order.setStatus(OrderStatus.PENDING);
    order.setOrderTime(LocalDateTime.now());
    order.setEstimatedDeliveryTime(LocalDateTime.now().plusMinutes(45));
    return repository.save(order);
  }

  @Transactional
  public FoodOrder payOrder(Long id, PaymentMethod method, BigDecimal amount) {
    FoodOrder order = repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    
    Payment payment = new Payment();
    payment.setAmount(amount);
    payment.setMethod(method);
    payment.setOrder(order);
    
    if (method == PaymentMethod.ONLINE) {
      payment.setStatus(PaymentStatus.COMPLETED);
      order.setStatus(OrderStatus.ACCEPTED);
    } else {
      payment.setStatus(PaymentStatus.PENDING);
      order.setStatus(OrderStatus.ACCEPTED);
    }
    
    order.setPayment(payment);
    return repository.save(order);
  }

  @Transactional
  public FoodOrder pickupOrder(Long id, Long courierId) {
    FoodOrder order = repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    order.setStatus(OrderStatus.IN_DELIVERY);
    order.setCourierId(courierId);
    return repository.save(order);
  }

  @Transactional
  public FoodOrder deliverOrder(Long id) {
    FoodOrder order = repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    order.setStatus(OrderStatus.DELIVERED);
    order.setDeliveryTime(LocalDateTime.now());
    
    if (order.getPayment() != null) {
      order.getPayment().setStatus(PaymentStatus.COMPLETED);
    }
    return repository.save(order);
  }

  public List<FoodOrder> getOrdersByCustomerId(Long customerId) {
    return repository.findAll((root, query, cb) -> cb.equal(root.get("customerId"), customerId));
  }
}

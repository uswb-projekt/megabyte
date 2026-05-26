package edu.prz.delivery.payments.domain.payment;

import edu.prz.delivery.foundation.exceptions.ConflictException;
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
public class PaymentService {

  private final PaymentRepository repository;

  public Optional<Payment> get(Long id) {
    return repository.findById(id);
  }

  public Payment update(Payment entity) {
    return repository.save(entity);
  }

  public void delete(Long id) {
    repository.deleteById(id);
  }

  public Page<Payment> list(Pageable pageable) {
    return repository.findAll(pageable);
  }

  public Page<Payment> list(Pageable pageable, Specification<Payment> filter) {
    return repository.findAll(filter, pageable);
  }

  public int count() {
    return (int) repository.count();
  }

  public Optional<Payment> getByOrderId(Long orderId) {
    return repository.findByOrderId(orderId);
  }

  public void validateTransition(PaymentStatus current, PaymentStatus target) {
    if (current == target) {
      return;
    }
    boolean allowed = false;
    switch (current) {
      case PENDING:
        allowed = (target == PaymentStatus.COMPLETED || target == PaymentStatus.FAILED);
        break;
      case COMPLETED:
        allowed = (target == PaymentStatus.REFUNDED);
        break;
      case FAILED:
      case REFUNDED:
        allowed = false; // Terminal states
        break;
    }
    if (!allowed) {
      throw new ConflictException("Niedozwolona zmiana statusu płatności z " + current + " na " + target);
    }
  }

  @Transactional
  public Payment patchStatus(Long id, PaymentStatus targetStatus) {
    Payment payment = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Payment not found with ID " + id));
    validateTransition(payment.getStatus(), targetStatus);
    payment.setStatus(targetStatus);
    return repository.save(payment);
  }
}

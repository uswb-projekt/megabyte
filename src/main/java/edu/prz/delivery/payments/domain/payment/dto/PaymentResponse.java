package edu.prz.delivery.payments.domain.payment.dto;

import edu.prz.delivery.payments.domain.payment.PaymentMethod;
import edu.prz.delivery.payments.domain.payment.PaymentStatus;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {

  private Long id;
  private int version;
  private BigDecimal amount;
  private PaymentStatus status;
  private PaymentMethod method;
}

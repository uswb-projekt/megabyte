package edu.prz.delivery.payments.domain.payment.dto;

import edu.prz.delivery.payments.domain.payment.PaymentMethod;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class CreatePaymentRequest {

  @NotNull(message = "Kwota płatności jest wymagana")
  @Positive(message = "Kwota płatności musi być dodatnia")
  @Schema(example = "36.00")
  private BigDecimal amount;

  @NotNull(message = "Metoda płatności jest wymagana")
  @Schema(example = "ONLINE")
  private PaymentMethod method;

  @NotNull(message = "ID zamówienia jest wymagane")
  @Schema(example = "1")
  private Long orderId;
}

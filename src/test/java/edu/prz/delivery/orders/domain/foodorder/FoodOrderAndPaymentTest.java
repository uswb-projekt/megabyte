package edu.prz.delivery.orders.domain.foodorder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.prz.delivery.payments.domain.payment.Payment;
import edu.prz.delivery.payments.domain.payment.PaymentMethod;
import edu.prz.delivery.payments.domain.payment.PaymentRepository;
import edu.prz.delivery.payments.domain.payment.PaymentStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class FoodOrderAndPaymentTest {

  @Autowired
  private FoodOrderRepository foodOrderRepository;

  @Autowired
  private PaymentRepository paymentRepository;

  @Test
  void testCreateFoodOrderWithItemsAndPayment() {
    // 1. Create FoodOrder
    FoodOrder order = new FoodOrder();
    order.setDescription("Large Pizza Order");
    order.setOrderTime(LocalDateTime.now());
    order.setEstimatedDeliveryTime(LocalDateTime.now().plusMinutes(45));
    order.setStatus(OrderStatus.PENDING);
    order.setCustomerId(100L);
    order.setRestaurantId(200L);
    order.setCourierId(300L);
    order.setDeliveryAddress("123 Main St, Warsaw");

    // 2. Add OrderItem
    OrderItem item1 = new OrderItem();
    item1.setProductId(1L);
    item1.setProductName("Margherita Pizza");
    item1.setQuantity(2);
    item1.setPrice(new BigDecimal("35.00"));
    item1.setNotes("Extra cheese");

    OrderItem item2 = new OrderItem();
    item2.setProductId(2L);
    item2.setProductName("Coca Cola");
    item2.setQuantity(3);
    item2.setPrice(new BigDecimal("8.00"));
    item2.setNotes("Cold");

    order.getItems().add(item1);
    order.getItems().add(item2);

    // 3. Save Order (Cascading Items)
    FoodOrder savedOrder = foodOrderRepository.save(order);
    assertNotNull(savedOrder.getId());
    assertEquals(2, savedOrder.getItems().size());
    assertNotNull(savedOrder.getItems().get(0).getId());

    // 4. Create and link Payment
    Payment payment = new Payment();
    payment.setAmount(new BigDecimal("94.00")); // (35*2) + (8*3)
    payment.setStatus(PaymentStatus.PENDING);
    payment.setMethod(PaymentMethod.ONLINE);
    payment.setOrder(savedOrder);

    Payment savedPayment = paymentRepository.save(payment);
    assertNotNull(savedPayment.getId());
    assertEquals(savedOrder.getId(), savedPayment.getOrder().getId());

    // 5. Query and verify relations
    Optional<FoodOrder> fetchedOrderOpt = foodOrderRepository.findById(savedOrder.getId());
    assertTrue(fetchedOrderOpt.isPresent());
    FoodOrder fetchedOrder = fetchedOrderOpt.get();
    assertEquals("Large Pizza Order", fetchedOrder.getDescription());
    assertEquals(2, fetchedOrder.getItems().size());
  }
}

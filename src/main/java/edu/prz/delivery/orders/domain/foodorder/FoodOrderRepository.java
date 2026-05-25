package edu.prz.delivery.orders.domain.foodorder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, Long>, JpaSpecificationExecutor<FoodOrder> {

}

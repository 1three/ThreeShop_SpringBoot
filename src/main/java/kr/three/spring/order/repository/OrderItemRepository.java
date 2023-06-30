package kr.three.spring.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.three.spring.order.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}

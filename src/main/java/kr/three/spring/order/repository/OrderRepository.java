package kr.three.spring.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.three.spring.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
	
}

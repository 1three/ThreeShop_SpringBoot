package kr.three.spring.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.three.spring.cart.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{
	// Repository: findBy, findAll, save
	
	
}

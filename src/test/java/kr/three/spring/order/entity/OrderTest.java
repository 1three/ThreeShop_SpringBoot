package kr.three.spring.order.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import kr.three.spring.item.constant.ItemSellStatus;
import kr.three.spring.item.entity.Item;
import kr.three.spring.item.repository.ItemRepository;
import kr.three.spring.order.repository.OrderRepository;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
class OrderTest {
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	@PersistenceContext
	EntityManager em;
	
	public Item createItem() {
		Item item = new Item();
		item.setItemNm("test product");
		item.setPrice(10000);
		item.setItemDetail("product detail");
		item.setItemSellStatus(ItemSellStatus.SELL);
		item.setStockNumber(10);
		item.setRegTime(LocalDateTime.now());
		item.setUpdateTime(LocalDateTime.now());
		return item;
	}
	
	@Test
	@DisplayName("영속성 전이 테스트")
	public void cascadeTest() {
		Order order = new Order();
		for(int i = 0; i < 3; i++) {
			Item item = this.createItem();
			itemRepository.save(item);
			OrderItem orderItem = new OrderItem();
			orderItem.setItem(item);
			orderItem.setCount(10);
			orderItem.setOrderPrice(1000);
			orderItem.setOrder(order);
			order.getOrderItems().add(orderItem);
		}
		
		orderRepository.saveAndFlush(order);
		em.clear();
		
		Order saveOrder = orderRepository
				.findById(order.getId())
				.orElseThrow(EntityNotFoundException::new);
		assertEquals(3, saveOrder.getOrderItems().size());
	}
}

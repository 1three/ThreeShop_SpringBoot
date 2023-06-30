package kr.three.spring.cart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import kr.three.spring.item.entity.Item;
import kr.three.spring.utils.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CartItem extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cart_item_id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY) // CartItem(me): Many - Cart: One
	@JoinColumn(name = "cart_id")
	private Cart cart;
	
	@ManyToOne(fetch = FetchType.LAZY) // CartItem(me): Many - Item: One
	@JoinColumn(name = "item_id")
	private Item item;
	
	private int count;
}

package kr.three.spring.order.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import kr.three.spring.member.entity.Member;
import kr.three.spring.order.constant.OrderStatus;
import kr.three.spring.utils.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "orders")
@NoArgsConstructor
public class Order extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY) // Order(me): Many - Member: One
	@JoinColumn(name = "member_id")
	private Member member;
	
	// mappedBy
	// owner: order, non-owner: orderItem
	@OneToMany(mappedBy = "order",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.LAZY)
	private List<OrderItem> orderItems = new ArrayList<>();
	
	private LocalDateTime orderDate;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
}

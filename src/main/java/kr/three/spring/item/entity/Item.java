package kr.three.spring.item.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import kr.three.spring.item.constant.ItemSellStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Item")
@Getter		// need
@Setter		// option
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // MySQL: IDENTITY
	@Column(name = "item_id")
	private Long id;			// product code
	
	@Column(nullable = false, length = 50)
	private String itemNm;		// product name
	
	@Column(nullable = false)
	private int price; 			// product price
	
	@Column(nullable = false, name="number")
	private int stockNumber;	// available stock
	
	@Enumerated(EnumType.STRING)
	private ItemSellStatus itemSellStatus;	// product sell status
	
	@Lob
	@Column(nullable = false)
	private String itemDetail;			// product Detail
	
	private LocalDateTime regTime;		// Register Time
	
	private LocalDateTime updateTime;	// Update Time
}

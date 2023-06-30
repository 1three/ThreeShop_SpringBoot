package kr.three.spring.item.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import kr.three.spring.item.constant.ItemSellStatus;
import kr.three.spring.item.dto.ItemFormDto;
import kr.three.spring.utils.entity.BaseEntity;
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
public class Item extends BaseEntity {
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
	
	// change detection
	public void updateItem(ItemFormDto itemFormDto) {
		this.itemSellStatus = itemFormDto.getItemSellStatus();
		this.itemNm = itemFormDto.getItemNm();
		this.price = itemFormDto.getPrice();
		this.stockNumber = itemFormDto.getStockNumber();
		this.itemDetail = itemFormDto.getItemDetail();
	}
}

package kr.three.spring.item.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
	private Long id;
	
	private String itemNm;
	
	private int price;
	
	private int stockNumber;
	
	private String itemSellStatus;
	
	private String itemDetail;
	
	private LocalDateTime regTime;
	
	private LocalDateTime updateTime;
}

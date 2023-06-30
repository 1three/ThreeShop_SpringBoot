package kr.three.spring.item.dto;

import kr.three.spring.item.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemSearchDto {
	
	private String searchDateType;
	
	private ItemSellStatus searchSellStatus;
	
	private String searchBy;
	
	private String searchQuery = "";
}

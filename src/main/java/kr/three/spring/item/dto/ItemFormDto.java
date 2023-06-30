package kr.three.spring.item.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import kr.three.spring.item.constant.ItemSellStatus;
import kr.three.spring.item.entity.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemFormDto {
	private Long id;
	
	@NotBlank(message = "상품명은 필수 항목입니다.")
	private String itemNm;
	
	@NotNull(message = "가격은 필수 항목입니다.")
	private int price;

	@NotNull(message = "재고는 필수 항목입니다.")
	private int stockNumber;

	@NotBlank(message = "상품 설명은 필수 항목입니다.")
	private String itemDetail;
	
	private ItemSellStatus itemSellStatus;
	
	private List<ItemImgDto> itemImgDtoList = new ArrayList<>();
	
	private List<Long> itemImgIds = new ArrayList<>();
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	// Convert DTO to Entity
	public Item creatItem() {
		return modelMapper.map(this, Item.class);
	}
	
	// Convert Entity to DTO
	public static ItemFormDto of(Item item) {
		return modelMapper.map(item, ItemFormDto.class);
	}
}

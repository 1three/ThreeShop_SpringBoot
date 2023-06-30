package kr.three.spring.item.dto;

import org.modelmapper.ModelMapper;

import groovy.transform.ToString;
import kr.three.spring.item.entity.ItemImg;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
public class ItemImgDto {
	
	private Long id;
	
	private String imgName;
	
	private String oriImgName; // prevent re-name
	
	private String imgUrl;
	
	private String repImgYn;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	// Convert Entity to DTO
	public static ItemImgDto of (ItemImg itemImg) {
		return modelMapper.map(itemImg, ItemImgDto.class);
	}
}

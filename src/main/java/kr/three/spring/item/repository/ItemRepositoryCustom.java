package kr.three.spring.item.repository;

import org.springframework.data.domain.Page;

import kr.three.spring.item.dto.ItemSearchDto;
import kr.three.spring.item.entity.Item;

public interface ItemRepositoryCustom {
	
	// Pageable: set page return number
	Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, org.springframework.data.domain.Pageable pageable);
}

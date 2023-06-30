package kr.three.spring.item.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.three.spring.item.dto.ItemFormDto;
import kr.three.spring.item.dto.ItemImgDto;
import kr.three.spring.item.dto.ItemSearchDto;
import kr.three.spring.item.entity.Item;
import kr.three.spring.item.entity.ItemImg;
import kr.three.spring.item.repository.ItemImgRepository;
import kr.three.spring.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {
	
	private final ItemRepository itemRepository;
	private final ItemImgRepository itemImgRepository;
	private final ItemImgService itemImgService;
	
	// Parameters retrieved from the web page: itemFormDto
	public Long saveItem
		(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws IOException {
		
		Item item = itemFormDto.creatItem(); // creatItem: Convert DTO to Entity
		itemRepository.save(item);
		
		for(int i = 0; i < itemImgFileList.size(); i++) {
			ItemImg itemImg = new ItemImg();
			itemImg.setItem(item);
			if(i == 0) {
				itemImg.setRepImgYn("Y");
			} else {
				itemImg.setRepImgYn("N");
			}
			itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
		}
		
		return item.getId();
	}
	
	// get item detail
	public ItemFormDto getItemDetail(Long itemId) {
		// ItemImg: Entity to DTO
		List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId); // Entity
		List<ItemImgDto> itemImgDtoList = new ArrayList<>(); // DTO List
		
		for(ItemImg itemImg : itemImgList) {
			ItemImgDto itemImgDto = ItemImgDto.of(itemImg);
			itemImgDtoList.add(itemImgDto);
		}
		
		// Item: Entity to DTO
		Item item = itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);
		ItemFormDto itemFormDto = ItemFormDto.of(item);
		
		// Add itemImgList related to Item to DTO
		itemFormDto.setItemImgDtoList(itemImgDtoList);		
		
		return itemFormDto;
	}
	
	// Update item/itemImg
	public Long updateItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws IOException {
		// Find item in DB using DTO's id
		Item item = itemRepository.findById(itemFormDto.getId()).orElseThrow(EntityNotFoundException::new);
		
		// Update item
		item.updateItem(itemFormDto);
		
		// Update itemImg
		List<Long> itemImgIds = itemFormDto.getItemImgIds();
		for(int i = 0; i < itemImgFileList.size(); i++) {
			itemImgService.updateItemImg(itemImgIds.get(i), itemImgFileList.get(i));
		}
		
		return item.getId();
	}
	
	public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
		return itemRepository.getAdminItemPage(itemSearchDto, pageable);
	}
}

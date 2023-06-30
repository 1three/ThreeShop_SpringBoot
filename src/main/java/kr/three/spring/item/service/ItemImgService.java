package kr.three.spring.item.service;

import java.io.IOException;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import kr.three.spring.item.entity.ItemImg;
import kr.three.spring.item.repository.ItemImgRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemImgService {
	@Value("${itemImgLocation}")
	private String itemImgLocation;
	
	private final ItemImgRepository itemImgRepository;
	
	private final FileService fileService;
	
	// 1. Save item image
	public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws IOException {
		String oriImgName = itemImgFile.getOriginalFilename();
		String imgName = "";
		String imgUrl = "";
		
		// Not(!) empty,
		if(!StringUtils.isEmpty(oriImgName)) {
			imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
			imgUrl = "/images/item/" + imgName;
		}
		
		itemImg.updateItemImg(oriImgName, imgName, imgUrl);
		itemImgRepository.save(itemImg); // save in DB
	}
	
	// 2. Update item image
	public void updateItemImg(Long itemImgId, MultipartFile itemImgFile) throws IOException {
		if(!itemImgFile.isEmpty()) {
			// get origin item image
			ItemImg itemImg = itemImgRepository.findById(itemImgId).orElseThrow(EntityNotFoundException::new);
			
			if(!StringUtils.isEmpty(itemImg.getImgName())) {
				// delete origin image
				fileService.deleteFile(itemImgLocation + "/" + itemImg.getImgName());
			}
			
			String oriImgName = itemImgFile.getOriginalFilename();
			String imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
			String imgUrl = "/images/item/" + imgName;
			
			// update
			itemImg.updateItemImg(oriImgName, imgName, imgUrl);
		}
	}
}

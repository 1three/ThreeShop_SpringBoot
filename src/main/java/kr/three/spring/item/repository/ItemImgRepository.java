package kr.three.spring.item.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.three.spring.item.entity.ItemImg;

public interface ItemImgRepository extends JpaRepository<ItemImg, Long>{
	
	// query method
	List<ItemImg> findByItemIdOrderByIdAsc(Long itemId);
}

package kr.three.spring.item.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import kr.three.spring.utils.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ItemImg extends BaseEntity { // extends BaseEntity: initial/last update time/men
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_img_id")
	private Long id;
	
	private String imgName;
	
	private String oriImgName; // prevent re-name
	
	private String imgUrl;
	
	private String repImgYn;
	
	@ManyToOne(fetch = FetchType.LAZY) // Many(ItemImg) - One(Item)
	@JoinColumn(name = "item_id") // FK mapping
	private Item item;

	public void updateItemImg(String imgName, String oriImgName, String imgUrl) {
		this.imgName = imgName;
		this.oriImgName = oriImgName;
		this.imgUrl = imgUrl;
	}
}

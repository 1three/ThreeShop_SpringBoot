package kr.three.spring.utils.entity;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity extends BaseTimeEntity {
	// 생성 사용자
	@CreatedBy
	@Column(updatable = false)
	private String createdBy;
	
	// 변경 사용자
	@LastModifiedBy
	private String modifiedBy;
	
}

package kr.three.spring.utils.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
@Getter
@Setter
public abstract class BaseTimeEntity {
	// 생성 날짜
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime regTime;
	
	// 수정 날짜
	@LastModifiedDate
	private LocalDateTime updateTime;
	
}

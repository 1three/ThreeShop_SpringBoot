package kr.three.spring.test.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Test {
	
	@Id // table's primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 20)
	private String myName;	// MySQL: my_name
	
	private int myAge;		// MySQL: my_age
	
	private String myInfo;	// MySQL: my_info
}

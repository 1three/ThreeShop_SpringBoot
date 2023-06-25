package kr.three.spring.member.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberFormDto {
	@NotBlank(message = "이름은 필수 항목입니다.")
	private String name;
	
	@NotEmpty(message = "이메일은 필수 항목입니다.")
	@Email
	private String email;
	
	@NotEmpty(message = "비밀번호는 필수 항목입니다.")
	@Length(min = 4, max = 12, message = "최소 4자, 최대 12자를 입력하세요.")
	private String password;
	
	@NotEmpty(message = "주소는 필수 항목입니다.")
	private String address;
}

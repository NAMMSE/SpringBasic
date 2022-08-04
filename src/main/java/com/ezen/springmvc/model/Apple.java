package com.ezen.springmvc.model;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// 0803

@ToString
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Apple {
	
	@NonNull // 기본형 타입에는 의미가 없기때문에 참조형에 붙여준다
	@Setter // 이런식으로 읽기 전용, 쓰기 전용 설정해줄 수 있다
	private Integer size;
	@NonNull
	@Setter
	private Integer price;
	private boolean red;
	private boolean fresh;
	
	public static void main(String[] args) {
		System.out.println(new Apple());
	}

}

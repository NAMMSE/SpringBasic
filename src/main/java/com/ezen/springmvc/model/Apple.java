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
	
	@NonNull // �⺻�� Ÿ�Կ��� �ǹ̰� ���⶧���� �������� �ٿ��ش�
	@Setter // �̷������� �б� ����, ���� ���� �������� �� �ִ�
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

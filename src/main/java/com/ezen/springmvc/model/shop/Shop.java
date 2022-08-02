package com.ezen.springmvc.model.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Shop {

	// 이런식으로 되면 생성자로 인해 문제가 생길 수 있으므로 어지간하면 Autowired를 사용하는 것이 좋다
	@Autowired
	Pos pos;
	
	@Autowired
	Milk milk;
	
	@Autowired
	Snack snack;
}

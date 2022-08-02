package com.ezen.springmvc.model;

import org.springframework.stereotype.Component;

// 0802 수업

@Component // 컴포넌트 어노테이션을 쓴 클래스에는 root-context에서 해당 클래스 내용을 수거해간다 컴포넌트 스캔을 통해 스프링 컨텍스트에 등록한다(src/spring/root-context.xml)
		//에 context 추가 하여 해당 클래스가 있는 패키지를 등록한다 
public class Coffee {
	private String name;
	private Integer price;
	private Boolean hot;
	
	// JavaBean Object가 되려면 기본 생성자가 반드시 존재해야 한다. (매개변수로 아무것도 가져오지 않아야 한다)
	public Coffee() {
		name = "default name";
		price = 0;
		hot = true;
	}
	
	public Coffee(String name, Integer price, Boolean hot) {
		this.name = name;
		this.price = price;
		this.hot = hot;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public boolean isHot() {
		return hot;
	}

	public void setHot(boolean hot) {
		this.hot = hot;
	}
	
	public String getTable() {
		return String.format("<table><tr></tr></table>");
	}
	
	@Override
	public String toString() {
		
		return String.format("%s/%s/%s", name, price, hot);
	}
	
	
}

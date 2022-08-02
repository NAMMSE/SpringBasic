package com.ezen.springmvc;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.springmvc.model.Coffee;
import com.ezen.springmvc.model.shop.Shop;

// 0802 수업

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired // 이렇게 적으면 생성자를 만들지 않아도(초기화) 알아서 컴포넌트 어노테이션을 적은 클래스에서 가장 적합한 인스턴스(coffee)에 해당 클래스의 생성자(컴포넌트)를 주입한다 
	Coffee coffee; // 초기화 안해주면 참조형은 null 값
	
	@Autowired
	Shop shop;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) { // 코드 진행을 보면 req에 어트리뷰트 실고 리턴해주는 것과 같지만 model 싣는게 차이점이다.
		logger.info("Welcome home! The client locale is {}.", locale);
		
		System.out.println("COFFEE : " + coffee);
		System.out.println("SHOP : " + shop);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("coffee", coffee );
		
		return "home"; // /로 접속을 했을때 home.jsp로 가라는 뜻
	}
	
}

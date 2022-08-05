package com.ezen.springmvc.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.springmvc.model.Coffee;
import com.ezen.springmvc.model.shop.Shop;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;

// 0802 수업

@Log4j2
@Log4j
@Controller
public class HomeController {
	
// private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//0803
	// @log4j 추가하면 그냥 쓸 수 있다
	//private Logger log = Logger.getLogger(this.getClass()); // 이 클래스에서 발생한 모든 로그를 log가 수집한다
	
	@Autowired // 이렇게 적으면 생성자를 만들지 않아도(초기화) 알아서 컴포넌트 어노테이션을 적은 클래스에서 가장 적합한 인스턴스(coffee)에 해당 클래스의 생성자(컴포넌트)를 주입한다 
	Coffee coffee; // 초기화 안해주면 참조형은 null 값
	
	@Autowired
	Shop shop;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) { // 코드 진행을 보면 req에 어트리뷰트 실고 리턴해주는 것과 같지만 model 싣는게 차이점이다.
// logger.info("Welcome home! The client locale is {}.", locale);
		
		//0802
		//System.out.println("COFFEE : " + coffee);
		//System.out.println("SHOP : " + shop);
		
		//0803
		log.debug("i am debug message");
		log.error("this is error meassage");
		log.info("informaiton");
		log.trace("trace message");
		
		log.warn("this is warn message");
		log.fatal("fatal");
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("coffee", coffee );// model에 어트리뷰트로 실어줄수도 있다.
		
		return "home"; // /로 접속을 했을때 home.jsp로 가라는 뜻
	}
	
}

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

// 0802 ����

@Log4j2
@Log4j
@Controller
public class HomeController {
	
// private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//0803
	// @log4j �߰��ϸ� �׳� �� �� �ִ�
	//private Logger log = Logger.getLogger(this.getClass()); // �� Ŭ�������� �߻��� ��� �α׸� log�� �����Ѵ�
	
	@Autowired // �̷��� ������ �����ڸ� ������ �ʾƵ�(�ʱ�ȭ) �˾Ƽ� ������Ʈ ������̼��� ���� Ŭ�������� ���� ������ �ν��Ͻ�(coffee)�� �ش� Ŭ������ ������(������Ʈ)�� �����Ѵ� 
	Coffee coffee; // �ʱ�ȭ �����ָ� �������� null ��
	
	@Autowired
	Shop shop;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) { // �ڵ� ������ ���� req�� ��Ʈ����Ʈ �ǰ� �������ִ� �Ͱ� ������ model �ƴ°� �������̴�.
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
		model.addAttribute("coffee", coffee );// model�� ��Ʈ����Ʈ�� �Ǿ��ټ��� �ִ�.
		
		return "home"; // /�� ������ ������ home.jsp�� ����� ��
	}
	
}

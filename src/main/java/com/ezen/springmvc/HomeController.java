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

// 0802 ����

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired // �̷��� ������ �����ڸ� ������ �ʾƵ�(�ʱ�ȭ) �˾Ƽ� ������Ʈ ������̼��� ���� Ŭ�������� ���� ������ �ν��Ͻ�(coffee)�� �ش� Ŭ������ ������(������Ʈ)�� �����Ѵ� 
	Coffee coffee; // �ʱ�ȭ �����ָ� �������� null ��
	
	@Autowired
	Shop shop;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) { // �ڵ� ������ ���� req�� ��Ʈ����Ʈ �ǰ� �������ִ� �Ͱ� ������ model �ƴ°� �������̴�.
		logger.info("Welcome home! The client locale is {}.", locale);
		
		System.out.println("COFFEE : " + coffee);
		System.out.println("SHOP : " + shop);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("coffee", coffee );
		
		return "home"; // /�� ������ ������ home.jsp�� ����� ��
	}
	
}

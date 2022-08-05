package com.ezen.springmvc.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.springmvc.model.Coffee;

import lombok.extern.log4j.Log4j2;


//0804

@RequestMapping(value= {"/hello", "/hi", "/welcome"})
@Controller
@Log4j2
public class HelloController {

	@RequestMapping(value = {"/news"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String news() {
		
		
		
		log.info("���� ��Ʈ�ѷ����� �� ������");
		
		
		// �������� ����ڿ��� �������� �� view�� �̸��� ���ڿ��� �����Ѵ�.
		//return "news"; // servlet-context�� prefix�κ��� news �տ� �ٰ� suffix�κ��� news �ڿ� �ٴ´�
						// �׷��� /springmvc/news�� ���� �� ���� ���� /WEB-INF/views/news.jsp�� �д´�
		 
		// ������ ���ڿ� �տ� /WEB-INF/views/ ���� �ٱ� ������ ���� ���ڿ����� �տ� /�� ���ش� / �̰� /WEB-INF/views/hello/news.jsp
		return "hello/news";
	}
	
	@GetMapping("/blog") // GetMapping ������ ������ ���� �÷����ؼ� pom���� 5.2.22�� �÷���
	public String blog() {
		
		return "hello/blog";
		
	}
	
	@GetMapping("/coffee/add")
	public String coffeeAddForm() {
		return "coffee/addMenu";
	}
	@PostMapping("/coffee/add1") //HelloController Ŭ���� �ȿ� �ִ� �޼����̱⶧���� /coffee/add �տ� /hello, /hi, /welcome �� �ϳ��� ����� �Ѵ�
	public String coffeeAdd1(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		
		request.setCharacterEncoding("EUC-KR"); // �� ������ ���ܴ���
		
		log.info("coffee add1 (�ڵ� ���ε��� ������� ����)");
		
		log.info("coffee name : " + request.getParameter("name"));
		log.info("coffee price : " + request.getParameter("price"));
		log.info("coffee hot : " + request.getParameter("hot"));
		
//		request.setAttribute("name", new Coffee(
//					request.getParameter("name"),
//					Integer.parseInt(request.getParameter("price")),
//					Boolean.parseBoolean(request.getParameter("hot"))
//				));
		
		//return "redirect:/"; // /�� redirect�̱� ������ HomeController�� �����Ѵ� (Ȩ��Ʈ�ѷ��� ������Ʈ���ο� value�� /��)
	
		return "home";
	}
	
	@PostMapping("/coffee/add2") 
	public String coffeeAdd2(Coffee coffee) {	
		
		log.info("coffee add2 (�ڵ� ���ε�)");
		
		log.info(coffee);
		
		if(coffee.isHot()) {
			log.info("�߰ſ� Ŀ�ǰ� �߰��Ǿ����ϴ�.");
		}else {
			log.info("������ Ŀ�ǰ� �߰��Ǿ����ϴ�.");
		}
	
		return "home";
	}
	
	@PostMapping("/coffee/add3") // �Ű������� ������
	public String coffeeAdd3(//String name,Integer price,Boolean hot) {
			@ModelAttribute("name") String name, 
			@ModelAttribute("price") Integer test,  // �𵨾�Ʈ����Ʈ ������̼��� ���� ���� �̸��� �ȸ��絵 �ȴ�
			@ModelAttribute("hot") Boolean hot) {
		// �Ű������� ������ ���ε��� �Ǵ°� ������ ��Ʈ����Ʈ�� �ڵ����� �߰��� ������ �ʴ´� / �߰��Ϸ��� @ModelAttribute�� ����ؾ��Ѵ�
			
		log.info("coffee add3 ");
			
		log.info("name : "+ name);
		log.info("price : "+ test);  //���⼱ test�� ����� �ȴ�
		log.info("hot : "+ hot);
		
		return "home";
	}
	
	@RequestMapping(value="/garden", method= RequestMethod.GET)
	public void garden() {
		log.info("garden controller");
	}
	
	@GetMapping(value="/redirect")
	public String redirect() {
		return "redirect:/hello/coffee/add";
		
	}
}

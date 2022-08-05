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
		
		
		
		log.info("뉴스 컨트롤러에는 잘 도착함");
		
		
		// 다음으로 사용자에게 보여져야 할 view의 이름을 문자열로 리턴한다.
		//return "news"; // servlet-context의 prefix부분이 news 앞에 붙고 suffix부분이 news 뒤에 붙는다
						// 그래서 /springmvc/news로 접속 시 다음 뷰인 /WEB-INF/views/news.jsp를 읽는다
		 
		// 리턴한 문자열 앞에 /WEB-INF/views/ 까지 붙기 때문에 리턴 문자열에는 앞에 /를 빼준다 / 이건 /WEB-INF/views/hello/news.jsp
		return "hello/news";
	}
	
	@GetMapping("/blog") // GetMapping 쓰려면 스프링 버전 올려야해서 pom에서 5.2.22로 올렸음
	public String blog() {
		
		return "hello/blog";
		
	}
	
	@GetMapping("/coffee/add")
	public String coffeeAddForm() {
		return "coffee/addMenu";
	}
	@PostMapping("/coffee/add1") //HelloController 클래스 안에 있는 메서드이기때문에 /coffee/add 앞에 /hello, /hi, /welcome 중 하나를 적어야 한다
	public String coffeeAdd1(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		
		request.setCharacterEncoding("EUC-KR"); // 얘 때문에 예외던짐
		
		log.info("coffee add1 (자동 바인딩을 사용하지 않음)");
		
		log.info("coffee name : " + request.getParameter("name"));
		log.info("coffee price : " + request.getParameter("price"));
		log.info("coffee hot : " + request.getParameter("hot"));
		
//		request.setAttribute("name", new Coffee(
//					request.getParameter("name"),
//					Integer.parseInt(request.getParameter("price")),
//					Boolean.parseBoolean(request.getParameter("hot"))
//				));
		
		//return "redirect:/"; // /로 redirect이기 때문에 HomeController로 접근한다 (홈컨트롤러에 리퀘스트매핑에 value가 /임)
	
		return "home";
	}
	
	@PostMapping("/coffee/add2") 
	public String coffeeAdd2(Coffee coffee) {	
		
		log.info("coffee add2 (자동 바인딩)");
		
		log.info(coffee);
		
		if(coffee.isHot()) {
			log.info("뜨거운 커피가 추가되었습니다.");
		}else {
			log.info("차가운 커피가 추가되었습니다.");
		}
	
		return "home";
	}
	
	@PostMapping("/coffee/add3") // 매개변수가 같으면
	public String coffeeAdd3(//String name,Integer price,Boolean hot) {
			@ModelAttribute("name") String name, 
			@ModelAttribute("price") Integer test,  // 모델어트리뷰트 어노테이션을 쓰면 굳이 이름을 안맞춰도 된다
			@ModelAttribute("hot") Boolean hot) {
		// 매개변수가 같으면 바인딩이 되는건 맞지만 어트리뷰트에 자동으로 추가가 되지는 않는다 / 추가하려면 @ModelAttribute를 사용해야한다
			
		log.info("coffee add3 ");
			
		log.info("name : "+ name);
		log.info("price : "+ test);  //여기선 test로 해줘야 된다
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

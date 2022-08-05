package com.ezen.springmvc.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.log4j.Log4j2;
//0805
@Log4j2
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
	"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
public class ControllerTest {
	
	@Autowired
	private WebApplicationContext context; // 이거 개념 뭔지 확인
	
	private MockMvc mockMvc; // 가짜 mvc
	
	@Before // 테스트 시작 전 실행되는 메서드
	public void testSetup() {
		// 매 테스트마다 MockMvcBuilder로 새로운 MockMvc를 생성한다
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testBlogController1() {
		// 처음 테스트 돌리면 서블렛 버전이 낮아서 java.lang.NoClassDefFoundError: javax/servlet/SessionCookieConfig 에러가 뜬다
		// 예전 서블렛에는 세션쿠키라는 개념이 없어서 에러가 나는 것으로 판단됨
		// 서블렛, jsp 버전 업데이트하고 정상적으로 테스트가 된다
		
		try {
			String nextView = mockMvc.perform(
					MockMvcRequestBuilders.get("/hi/blog"))//; 가짜 요청을 만들어서 get방식으로 요청을 보내는 것
					.andReturn().getModelAndView().getViewName(); // 리턴 값 중에 모델과 뷰를 꺼내서 그중에 뷰의 이름을 문자열로 반환한다
					// hellocontroller에 @blog의 리턴 값이 hello/blog이기 때문에 nextview의 값이 hello/blog인것
			log.info("next view : " + nextView);
			assertEquals("hello/blog", nextView);

		} catch (Exception e) {
			fail();
			
		}
	}
	
}

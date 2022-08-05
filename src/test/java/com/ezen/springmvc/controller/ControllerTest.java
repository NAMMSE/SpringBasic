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
	private WebApplicationContext context; // �̰� ���� ���� Ȯ��
	
	private MockMvc mockMvc; // ��¥ mvc
	
	@Before // �׽�Ʈ ���� �� ����Ǵ� �޼���
	public void testSetup() {
		// �� �׽�Ʈ���� MockMvcBuilder�� ���ο� MockMvc�� �����Ѵ�
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testBlogController1() {
		// ó�� �׽�Ʈ ������ ���� ������ ���Ƽ� java.lang.NoClassDefFoundError: javax/servlet/SessionCookieConfig ������ ���
		// ���� �������� ������Ű��� ������ ��� ������ ���� ������ �Ǵܵ�
		// ����, jsp ���� ������Ʈ�ϰ� ���������� �׽�Ʈ�� �ȴ�
		
		try {
			String nextView = mockMvc.perform(
					MockMvcRequestBuilders.get("/hi/blog"))//; ��¥ ��û�� ���� get������� ��û�� ������ ��
					.andReturn().getModelAndView().getViewName(); // ���� �� �߿� �𵨰� �並 ������ ���߿� ���� �̸��� ���ڿ��� ��ȯ�Ѵ�
					// hellocontroller�� @blog�� ���� ���� hello/blog�̱� ������ nextview�� ���� hello/blog�ΰ�
			log.info("next view : " + nextView);
			assertEquals("hello/blog", nextView);

		} catch (Exception e) {
			fail();
			
		}
	}
	
}

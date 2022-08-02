package com.ezen.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//0802 ����

public class CalculatorTest {
	
	Calculator calc = new Calculator(); // �ٸ� ������ ������ ���� ��Ű���̱� ������ import�� �ʿ� ����
	
	static int i;
	int a, b;
	
	@Before
	public void before() {
		System.out.println("Execute @Before(" + ++i +")");
		a=10;
		b=20;
	}
	
	@After
	public void after() {
		System.out.println("Execute @After(" + i + ")");
	}

	@Test // Test ������̼��� �־�� �׽�Ʈ�� �Ѵ�
	public void test() {
		// fail("Not yet implemented"); �ʱⰪ
		int result = calc.add(a, b); // before���� �ʱ�ȭ�� a,b�� �����´� �׽�Ʈ ���� �� Before�� �����ϴϱ�
		
		assertEquals(30, result);
		
		System.out.println("test1");
	}

//	@Test
//	public void randomTest1() {
//		for(int i=10 ; i<15;i++) { // �������� ���� ���и� �Ѵ�
//			assertTrue(calc.randomInt(i) < i-1);
//		}
//		System.out.println("test2");
//	}
	
	
	
	/*
	 	(1) �����ϸ� ¦���� ���� ������ �����Ͽ� ��ȯ�ϴ� �޼��带 ������ �� �˸��� �׽�Ʈ ���̽��� �ۼ�
	 	
	 	(2) � ���ڸ� �ϳ� �����ϸ� �Ҽ����� �ƴ��� �Ǻ����ִ� �޼��带 ������ �� �˸��� �׽�Ʈ ���̽��� �ۼ�
	 */
	
	@Test
	public void randomEvenTest() {
		int test = calc.randomEven();
		for(int i=0;i<30;i++) {
			assertTrue(test%2==0);
		}
	}
	
	@Test
	public void checkPrimeTest() {
		int test = (int)((Math.random()*100)+1);
		System.out.println(test);
		assertTrue(calc.checkPrime(test));
	}
	
}

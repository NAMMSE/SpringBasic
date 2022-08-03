package com.ezen.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

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
		for(int i=0;i<30;i++) {
			int test = calc.randomEven();
			assertTrue(test%2==0);
		}
	}
	
	@Test
	public void checkPrimeTest() {
		int test = (int)((Math.random()*28)+1);
		System.out.println("test num : "+test);
		assertTrue(calc.checkPrime(test));
	}
	
	// ����� �ڵ�
	@Test
	public void getEvenTest1() {
		int num = calc.getEven();
		assertTrue(num%2==0);
	}
	
	@Test
	public void getEvenTest2() {
		int num = calc.getEven();
		assertFalse(num%2==1);
	}
	
	@Test
	public void getEvenTest3() {
		ArrayList<Integer> evens = new ArrayList<>(); // JRE������ ���Ƽ� arrayList�� ���� pom.xml���� ���� �÷��ָ� �ȴ�
		
		for(int i=0;i<20000;i++) {
			evens.add(calc.getEven());
		}
		
		assertFalse("evens�� 0�� �߰ߵǾ����ϴ�",evens.contains(0));
	}
	
	@Test
	public void isPrimeTest1() {
		assertTrue(calc.isPrime(13));
	}
	
	@Test
	public void isPrimeTest2() {
		assertFalse(calc.isPrime(14));
	}
	
	@Test
	public void isPrimeTest3() {
		assertFalse("1�� �Ҽ��� �Ǻ���",calc.isPrime(1));
	}
	
	@Test
	public void isPrimeTest4() {
		assertFalse("-7�� �Ҽ��� �Ǻ���",calc.isPrime(-7));
	}
	
}

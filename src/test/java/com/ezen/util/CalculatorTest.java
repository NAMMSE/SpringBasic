package com.ezen.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//0802 수업

public class CalculatorTest {
	
	Calculator calc = new Calculator(); // 다른 폴더에 있지만 같은 패키지이기 때문에 import가 필요 없다
	
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

	@Test // Test 어노테이션이 있어야 테스트를 한다
	public void test() {
		// fail("Not yet implemented"); 초기값
		int result = calc.add(a, b); // before에서 초기화한 a,b를 가져온다 테스트 실행 전 Before를 실행하니까
		
		assertEquals(30, result);
		
		System.out.println("test1");
	}

//	@Test
//	public void randomTest1() {
//		for(int i=10 ; i<15;i++) { // 랜덤으로 성공 실패를 한다
//			assertTrue(calc.randomInt(i) < i-1);
//		}
//		System.out.println("test2");
//	}
	
	
	
	/*
	 	(1) 실행하면 짝수인 양의 정수를 생성하여 반환하는 메서드를 정의한 후 알맞은 테스트 케이스를 작성
	 	
	 	(2) 어떤 숫자를 하나 전달하면 소수인지 아닌지 판별해주는 메서드를 정의한 후 알맞은 테스트 케이스를 작성
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
	
	// 강사님 코드
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
		ArrayList<Integer> evens = new ArrayList<>(); // JRE버전이 낮아서 arrayList가 없다 pom.xml에서 버전 올려주면 된다
		
		for(int i=0;i<20000;i++) {
			evens.add(calc.getEven());
		}
		
		assertFalse("evens에 0이 발견되었습니다",evens.contains(0));
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
		assertFalse("1을 소수로 판별함",calc.isPrime(1));
	}
	
	@Test
	public void isPrimeTest4() {
		assertFalse("-7을 소수로 판별함",calc.isPrime(-7));
	}
	
}

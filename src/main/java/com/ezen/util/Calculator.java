package com.ezen.util;

// 0802 수업

public class Calculator {

	public int add(int a, int b) {
		return a+b;
	}
	
	public int randomInt(int size) {
		return (int)(Math.random()*size);
	}
	
	public int randomEven() {
		int even = (int)(((Math.random()*100)+1));
		if(even%2==0) {
			return even;
		}else
			return 2;
	}
	
	public boolean checkPrime(int num) {
		for(int i=2;i<num;i++) {
			if(num%i==0||num<=1) {
				return false;
			}
		}
		return true;
	}

	// 강사님 코드
	// 짝수 생성
	public int getEven() {
		int num = (int)(Math.random()*10000);
		return num % 2==0?num : num+1;
	}
	
	// 소수 판별
	
	public boolean isPrime(int num) {
		if(num < 2)
			return false;
		double sqrt = Math.sqrt(num);
		for(int i = 2; i<=sqrt ; i++) {
			if(num%i==0) {
				return false;
			}
		}
		return true;
	}
}

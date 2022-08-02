package com.ezen.util;

// 0802 ¼ö¾÷

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
			if(num%i==0) {
				return false;
			}
		}
		return true;
	}
	
}

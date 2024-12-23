package com.wplab.eltest;

public class MyMath {

	public static int intervalSum(int num1, int num2) {
		int sum = 0;
		
		if (num1 < num2) {
			for(int i=num1; i<=num2; i++)
				sum =+ i;
		}else if(num1 == num2 ) {
			sum = num1;
		}
		
		return sum;
	}
	
}


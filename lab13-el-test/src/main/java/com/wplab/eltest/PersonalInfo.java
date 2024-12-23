package com.wplab.eltest;

import java.util.Date;

public class PersonalInfo {
	private String name;
	private char gender;
	private int birthYear;
	private boolean married;
	
	public PersonalInfo() {
	//매개변수를 가진 생성자가 있으면 매개변수가 없는 생성자가 있어야한다.
	}
	
	public PersonalInfo(String name, char gender, int age, boolean married) {
		this.name = name;
		this.gender = gender;
		setAge(age);
		this.married = married;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public char getGender() {
		return gender;
	}
	
	public void setGender(char gender) {
		this.gender = gender;
	}
	
	public int getAge() {
		return (new Date()).getYear() - birthYear;
	}
	
	public void setAge(int age) {
		this.birthYear = (new Date()).getYear() - age;
	}

	public boolean isMarried() {
		return married;
	}

	public void setMarried(boolean married) {
		this.married = married;
	}

	@Override
	public String toString() {
		return "PersonalInfo [name=" + name + ", gender=" + gender + ", age=" + getAge() + ", married=" + married
				+ "]";
	}
	
	
}

package com.wplab.personalitytest.service;

public class TestResultDO {
	private int score;
	private String result;
	
	public TestResultDO() {
		// TODO Auto-generated constructor stub
	}
	
	public TestResultDO(int score, String result) {
		this.score = score;
		this.result = result;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}	

}

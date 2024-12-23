package com.wp.personalitytest;

public class TestResultDO {
	private int score;
	private String result;
	
	public TestResultDO() {
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

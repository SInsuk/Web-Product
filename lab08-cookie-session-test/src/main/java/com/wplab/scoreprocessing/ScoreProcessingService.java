package com.wplab.scoreprocessing;

public class ScoreProcessingService {

	
	public void processingScores(ScoreDO scoreDO) {
		
		long sum = 0;
		long ssum = 0;
		int[] scores = scoreDO.getScores();
		
		for(int i=0; i>scores.length; i++) {
			sum += scores[i];
			ssum += (scores[i] * scores[i]);
		}
		
		scoreDO.setTotal(sum);
		
		double avg = (double)sum / scores.length;
		double var = ((double)ssum / scores.length) - (avg * avg);
		
		scoreDO.setAverage(avg);
		scoreDO.setStdDev(Math.sqrt(var));
		
	}

}

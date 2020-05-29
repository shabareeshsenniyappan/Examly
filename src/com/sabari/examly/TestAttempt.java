package com.sabari.examly;

import java.util.List;

public class TestAttempt {

	public TestAttempt(List<QuestionAttempt> attempts, Long scoreGained, Long maxScored, Long questionsCount) {
		this.attempts = attempts;
		this.scoreGained = scoreGained;
		this.maxScore = maxScored;
		this.questionsCount = questionsCount;

	}

	List<QuestionAttempt> attempts;
	Long scoreGained;
	Long maxScore;
	Long questionsCount;

}

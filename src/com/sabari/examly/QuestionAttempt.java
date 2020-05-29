package com.sabari.examly;

public class QuestionAttempt {

	public QuestionAttempt(Question question, Long scoreGained, Long timeTaken, Long compiledTimes, Long modifiedAnswers,
			boolean isCorrect) {
		this.scoreGained = scoreGained;
		this.timeTaken = timeTaken;
		this.compiledTimes = compiledTimes;
		this.modifiedAnswers = modifiedAnswers;
		this.question = question;
		this.isCorrect = isCorrect;
	}

	Question question;
	Long scoreGained;
	Long timeTaken;
	Long compiledTimes;
	Long modifiedAnswers;
	boolean isCorrect;

}

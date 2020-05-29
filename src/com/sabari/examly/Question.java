package com.sabari.examly;

public class Question {

	public Question(Long maxScore, Language language, QuestionType type, DifficultyLevel level, Long avgTime,
			Long noOfCrctResponses, Long noOfWrongResponses) {

		this.maxScore = maxScore;
		this.language = language;
		this.type = type;
		this.level = level;
		this.avgTime = avgTime;
		this.noOfCrctResponses = noOfCrctResponses;
		this.noOfWrongResponses = noOfWrongResponses;

	}
	Long maxScore;
	Language language;
	QuestionType type;
	DifficultyLevel level;
	Long avgTime;
	Long noOfCrctResponses;
	Long noOfWrongResponses;
}

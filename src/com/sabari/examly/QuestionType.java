package com.sabari.examly;

public enum QuestionType {
	PROGRAM(85L), MCQ(5L), FILL_UP(10L);

	public Long value;

	QuestionType(Long value) {
		this.value = value;
	}
}

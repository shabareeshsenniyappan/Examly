package com.sabari.examly;

public enum DifficultyLevel {

	LOW(10), MEDIUM(60), HIGH(100);

	public double value;

	DifficultyLevel(double value) {
		this.value = value;
	}
}

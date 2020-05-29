package com.sabari.examly;

import java.util.List;
import java.util.Map;

public class Student {

	public Student(String name, List<TestAttempt> attempts, Map<Language, Long> video) {
		this.name = name;
		this.attempts = attempts;
		this.video = video;

	}

	public String name;
	public List<TestAttempt> attempts;
	public Map<Language, Long> video;

}

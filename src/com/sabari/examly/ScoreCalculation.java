package com.sabari.examly;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public 	class ScoreCalculation {
	public Map<Language, Long> calcQuestTypeBasedAvgScore(Student currStudent) {
		Map<Language, Map<DifficultyLevel, Long>> langDifficultyBasedCount = new HashMap<>();
		Map<Language, Map<DifficultyLevel, Long>> langDifficultyBasedScore = new HashMap<>();
		for (TestAttempt testAttempt : currStudent.attempts) {
			for (QuestionAttempt quesAttempt : testAttempt.attempts) {
				Long weight = calcTypeBaseWeightage(quesAttempt, currStudent);
				if (langDifficultyBasedScore.get(quesAttempt.question.language) == null) {
					Map<DifficultyLevel, Long> scoreDifficultyMap = new HashMap<>();
					Long prioritizedValue = (long) ((weight * quesAttempt.question.type.value)
							+ (weight * quesAttempt.question.level.value));
					scoreDifficultyMap.put(quesAttempt.question.level, prioritizedValue);
					langDifficultyBasedScore.put(quesAttempt.question.language, scoreDifficultyMap);
					Map<DifficultyLevel, Long> difficultyCountMap = new HashMap<>();
					difficultyCountMap.put(quesAttempt.question.level,
							quesAttempt.question.type.value + (long) (quesAttempt.question.level.value));
					langDifficultyBasedCount.put(quesAttempt.question.language, difficultyCountMap);
				} else {
					Map<DifficultyLevel, Long> scoreDifficultyMap = langDifficultyBasedScore
							.get(quesAttempt.question.language);
					Map<DifficultyLevel, Long> difficultyCountMap = langDifficultyBasedCount
							.get(quesAttempt.question.language);
					if (scoreDifficultyMap.get(quesAttempt.question.level) == null) {
						scoreDifficultyMap.put(quesAttempt.question.level, (weight * quesAttempt.question.type.value)
								+ (long) (weight * quesAttempt.question.level.value));
						difficultyCountMap.put(quesAttempt.question.level,
								quesAttempt.question.type.value + (long) quesAttempt.question.level.value);
					} else {
						Long existingScore = scoreDifficultyMap.get(quesAttempt.question.level);
						Long prioritizedValue = (long) ((existingScore + (weight * quesAttempt.question.type.value)
								+ (weight * quesAttempt.question.level.value)));
						scoreDifficultyMap.put(quesAttempt.question.level, prioritizedValue);
						difficultyCountMap.put(quesAttempt.question.level,
								difficultyCountMap.get(quesAttempt.question.level) + quesAttempt.question.type.value
										+ (long) quesAttempt.question.level.value);
					}
					langDifficultyBasedScore.put(quesAttempt.question.language, scoreDifficultyMap);
					langDifficultyBasedCount.put(quesAttempt.question.language, difficultyCountMap);
				}
			}
		}
		return resolveDifficultyWeightage(langDifficultyBasedScore, langDifficultyBasedCount);
	}

	public Map<Language, Long> resolveDifficultyWeightage(
			Map<Language, Map<DifficultyLevel, Long>> langDifficultyBasedScore,
			Map<Language, Map<DifficultyLevel, Long>> langDifficultyBasedCount) {
		Map<Language, Long> langWeightMap = new HashMap<>();
		for (Entry<Language, Map<DifficultyLevel, Long>> entry : langDifficultyBasedScore.entrySet()) {
			Language currLanguage = entry.getKey();
			Map<DifficultyLevel, Long> difficultyBasedWeight = entry.getValue();
			Long questWeightage = 0L, scoreWeightage = 0L;
			for (Entry<DifficultyLevel, Long> entry1 : difficultyBasedWeight.entrySet()) {
				DifficultyLevel currLevel = entry1.getKey();
				Long score = entry1.getValue();
				double diffBaseWeight = currLevel.value;
				Long quesCount = langDifficultyBasedCount.get(currLanguage).get(currLevel);
				Long weight = (long) Math.ceil((score) / quesCount);
				questWeightage += quesCount;
				scoreWeightage += score;
				difficultyBasedWeight.put(currLevel, weight);
			}
			scoreWeightage = scoreWeightage / questWeightage;
			langWeightMap.put(currLanguage, scoreWeightage);
		}
		return langWeightMap;
	}

	public Long calcTypeBaseWeightage(QuestionAttempt questionAttempt, Student currStudent) {
		Question question = questionAttempt.question;
		Long scoreGained = questionAttempt.scoreGained;
		Long maxScore = question.maxScore;
		boolean isCorrect = questionAttempt.isCorrect;
		Long crctResponses = question.noOfCrctResponses;
		Long wrongResponses = question.noOfWrongResponses;
		Long timeTaken = questionAttempt.timeTaken;
		Long avgTime = question.avgTime;
		Long modifiedAnswers = questionAttempt.modifiedAnswers;
		Long compiledTime = questionAttempt.compiledTimes;
		Map video = currStudent.video;
		double scorePercentage = (scoreGained != 0 && maxScore != 0) ? ((double) scoreGained / (double) maxScore) * 100
				: 0;
		double timeTakePercentage = (timeTaken != 0 && avgTime != 0) ? ((double) avgTime / (double) timeTaken) : 0;
		if (!isCorrect && crctResponses < wrongResponses) {
			Long responsesPercent = (long) (10 * (double) crctResponses / (crctResponses + wrongResponses));
			scorePercentage = scorePercentage + (scorePercentage * responsesPercent) / 100;
		}
		if (currStudent.video.get(question.language) > 10) {
			Long maxPercent = 5L;
			Long videoViewed = currStudent.video.get(question.language) - 10;
			videoViewed = videoViewed / 10;
			if (videoViewed > maxPercent) {
				scorePercentage -= maxPercent;
			} else {
			scorePercentage-= videoViewed;
			}
		}
		if (isCorrect && timeTakePercentage > 0) {
			Long timeBasedPercentage;
			if (timeTakePercentage > 1) {
				if (timeTakePercentage >= 2) {
					if (question.type == QuestionType.PROGRAM) {
						timeBasedPercentage = 20L;
					} else {
						timeBasedPercentage = 5L;
					}
				} else {
					double timeMargin = timeTaken - (double) ((double) avgTime / 2);
					double timeMarginPercentage = 100 * timeMargin / ((double) avgTime / 2);
					if (question.type == QuestionType.PROGRAM) {
						timeBasedPercentage = (long) timeMarginPercentage * 20 / 100;
					} else
						timeBasedPercentage = (long) timeMarginPercentage * 5 / 100;
				}
			} else if (timeTakePercentage < 1) {
				if (timeTakePercentage < 0.5) {
					timeBasedPercentage = 0L;
				} else {
					double timeMargin = timeTaken - avgTime;
					double timeMarginPercentage = timeMargin * 100 / (double) avgTime;
					if (question.type == QuestionType.PROGRAM) {
						timeBasedPercentage = (long) ((double) timeMarginPercentage * 20 / 100);
					} else {
						timeBasedPercentage = (long) ((double) timeMarginPercentage * 5 / 100);
					}
				}
			} else {
				timeBasedPercentage = 8L;
			}
			if (question.type == QuestionType.PROGRAM) {
				scorePercentage = ((double) scorePercentage * 80 / 100) + timeBasedPercentage;
			} else {
				scorePercentage = ((double) scorePercentage * 95 / 100) + timeBasedPercentage;
			}
		}
		Long percentToReduce = 0L;
		switch (question.type) {
		case MCQ:
			if (isCorrect && modifiedAnswers > 1) {
				Long maxPercent = 5L;
				modifiedAnswers = modifiedAnswers / 2;
				if (modifiedAnswers > maxPercent) {
					percentToReduce = maxPercent;
				} else {
					percentToReduce = modifiedAnswers;
				}
			}
			break;
		case PROGRAM:
			if (isCorrect && compiledTime > 10) {
				compiledTime = compiledTime - 10;
				Long maxPercent = 10L;
				compiledTime = compiledTime / 5;
				if (compiledTime > maxPercent) {
					percentToReduce = maxPercent;
				} else {
					percentToReduce = compiledTime;
				}
			}
			break;
		case FILL_UP:
			// No Criteria to reduce percentage for FILL_UP types
			break;
		default:
			break;
		}
		if (percentToReduce > 0) {
			scorePercentage -= percentToReduce;
		}
		return (long) scorePercentage;
	}
}
package itvac;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class ExamlyProject {


	public static void main(String args[]) throws FileNotFoundException {
			Scanner in=new Scanner(System.in);
			System.out.println("Enter the File Path : ");
			ScoreCalculation sc = new ScoreCalculation();
			long start = System.currentTimeMillis();
			BufferedReader reader = new BufferedReader(new FileReader(in.nextLine()));
			try {
				String name = reader.readLine();
				int n = Integer.parseInt(reader.readLine());
				List<QuestionAttempt> qaList=new ArrayList<>();
				for (int i = 0; i < n; i++) {
					String question[] = reader.readLine().split(" ");
					Question Q = new Question(Long.parseLong(question[0]), Language.valueOf(question[1]),
							QuestionType.valueOf(question[2]), DifficultyLevel.valueOf(question[3]),
							Long.parseLong(question[4]), Long.parseLong(question[5]), Long.parseLong(question[6]));
					String questionAttempt[] = reader.readLine().split(" ");
					QuestionAttempt qa = new QuestionAttempt(Q, Long.parseLong(questionAttempt[0]),
							Long.parseLong(questionAttempt[1]), Long.parseLong(questionAttempt[2]),
							Long.parseLong(questionAttempt[3]), Boolean.parseBoolean(questionAttempt[4]));
					qaList.add(qa);
					
				}
				int m=Integer.parseInt(reader.readLine());
				Map<Language, Long> video = new HashMap<Language, Long>();
				for(int i=0;i<m;i++)
				{
					String str[]=reader.readLine().split(" ");
					video.put(Language.valueOf(str[0]), Long.parseLong(str[1]));
				}
				reader.close();
				 start = System.currentTimeMillis();
				TestAttempt ta = new TestAttempt(qaList, null, null,null);
				Student student = new Student(name, Arrays.asList(ta), video);
				Map<Language, Long> langBasedScore = sc.calcQuestTypeBasedAvgScore(student);
				System.out.println("Score of " + student.name + " for");
				for (Entry<Language, Long> entry : langBasedScore.entrySet()) {
					System.out.println("\t" + entry.getKey() + " is : " + entry.getValue());
				}
				System.out.println();
	
			} catch (IOException e) {
				e.printStackTrace();
			}
			long end = System.currentTimeMillis();
			Date date = new Date(end-start);
			System.out.println("The Program is Executed for : "+(end-start)+" milliseconds");	
	}
}

class ScoreCalculation {
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

class Student {

	Student(String name, List<TestAttempt> attempts, Map<Language, Long> video) {
		this.name = name;
		this.attempts = attempts;
		this.video = video;

	}

	public String name;
	public List<TestAttempt> attempts;
	public Map<Language, Long> video;

}

class TestAttempt {

	TestAttempt(List<QuestionAttempt> attempts, Long scoreGained, Long maxScored, Long questionsCount) {
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

class QuestionAttempt {

	QuestionAttempt(Question question, Long scoreGained, Long timeTaken, Long compiledTimes, Long modifiedAnswers,
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

class Question {

	Question(Long maxScore, Language language, QuestionType type, DifficultyLevel level, Long avgTime,
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

enum Language {
	JAVA, C, C_PLUS_PLUS, DOT_NET, PYTHON, WEB,
}

enum QuestionType {
	PROGRAM(85L), MCQ(5L), FILL_UP(10L);

	public Long value;

	QuestionType(Long value) {
		this.value = value;
	}
}

enum DifficultyLevel {

	LOW(10), MEDIUM(60), HIGH(100);

	public double value;

	DifficultyLevel(double value) {
		this.value = value;
	}
}

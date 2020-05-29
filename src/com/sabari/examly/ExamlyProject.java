package com.sabari.examly;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

class ExamlyProject {

	


		public static void main(String args[]) throws IOException {
			
			URL url = new URL("https://raw.githubusercontent.com/shabareeshsenniyappan/Examly/master/100%20Questions.txt");
	        BufferedReader reader = new BufferedReader(
	        new InputStreamReader(url.openStream()));
//	        String r;
//	        while ((r = read.readLine()) != null)
//	            System.out.println(r);
	       // read.close();

			
			
			
				Scanner in=new Scanner(System.in);
				System.out.println("Enter the File Path : ");
				ScoreCalculation sc = new ScoreCalculation();
				long start = System.currentTimeMillis();
				//BufferedReader read = new BufferedReader(new FileReader(in.nextLine()));
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


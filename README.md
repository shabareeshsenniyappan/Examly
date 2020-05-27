# Examly
Project
Derive an algorithm for efficiently predicting the Coding skill score of a student in a particular Language given the following data.

input format (File):
First line of the input file is name of the Student.
Second line of the input is n number of test attended by a student.
Following n*2 number of lines
       first line consist of space separated inputs of:
            Total score of the question.
            Programming language (JAVA, C, C_PLUS_PLUS, DOT_NET, PYTHON).
            Question Type (PROGRAM, MCQ, FILL_UP).
            Difficulty Level (LOW, MEDIUM, HIGH).
            Average time taken.
            No.of correct responses.
            NO. of wrong responses.
       second line consist of space separated inputs of:
            Scored gained by a student.
            Time taken by a student to answer the question.
            No.of times programming question compiled.
            No.of times MCQ answers were changed.
            Answer of a student is correct or wrong (true/false).
Next line consist of N number of learning videos viewed by a student
       following N lines of space separated:
            Language of the video (JAVA, C, C_PLUS_PLUS, DOT_NET, PYTHON).
            No. of videos viewed.
      
      
Sample input:
//file//
Sabareesh
5
100 C_PLUS_PLUS MCQ MEDIUM 689 26 74
72 27 22 6 true
100 C FILL_UP HIGH 802 7 93
38 3873 48 21 false
100 JAVA FILL_UP HIGH 784 43 57
19 1394 39 5 false
100 JAVA MCQ LOW 890 71 29
43 161 14 25 true
100 C_PLUS_PLUS PROGRAM MEDIUM 1516 82 18
12 2374 13 21 false
4
JAVA 44
C 1
C_PLUS_PLUS 17
DOT_NET 2

sample output:
Score of Sabareesh for
	JAVA is : 20
	C_PLUS_PLUS is : 29
	C is : 38
The Program is Executed for : 1359 milliseconds

      
      

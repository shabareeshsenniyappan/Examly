# Examly
Project
Derive an algorithm for efficiently predicting the Coding skill score of a student in a particular Language given the following data.

input format (URL Of the File):
First line of the input file is name of the Student.
Second line of the input is n number of tests attended by a student.
Following n*2 number of lines
       first line consist of space separated inputs of:
            Total score of the question.
            Programming language (JAVA, C, C_PLUS_PLUS, DOT_NET, PYTHON).
            Question Type (PROGRAM, MCQ, FILL_UP).
            Difficulty Level (LOW, MEDIUM, HIGH).
            Average time taken.
            No.of correct responses.
            NO. of wrong responses.
       second line consists of space separated inputs of:
            Scored gained by a student.
            Time taken by a student to answer the question.
            No.of times programming question compiled.
            No.of times MCQ answers were changed.
            Answer of a student is correct or wrong (true/false).
Next line consists of N number of learning videos viewed by a student
       following N lines of space separated:
            Language of the video (JAVA, C, C_PLUS_PLUS, DOT_NET, PYTHON).
            No. of videos viewed.
SAMPLE URL OF THE FILE  
    100 input file  https://raw.githubusercontent.com/shabareeshsenniyappan/Examly/master/100%20Questions.txt
    10000 input file  https://raw.githubusercontent.com/shabareeshsenniyappan/Examly/master/10000%20Questions.txt
    100000 input file https://raw.githubusercontent.com/shabareeshsenniyappan/Examly/master/100000%20Questions.txt
Sample input:
//URL file//

https://raw.githubusercontent.com/shabareeshsenniyappan/Examly/master/100%20Questions.txt

Sabareesh
100
100 C FILL_UP MEDIUM 717 29 71
80 2065 7 1 true
100 C_PLUS_PLUS FILL_UP HIGH 860 83 17
88 1909 14 17 true
100 JAVA PROGRAM HIGH 1641 78 22
87 3518 26 13 true
100 DOT_NET PROGRAM HIGH 637 56 44
71 260 34 24 true
100 C FILL_UP MEDIUM 1000 49 51
81 3978 36 15 true
100 C_PLUS_PLUS PROGRAM LOW 558 30 70
76 2333 12 6 true
100 C MCQ HIGH 116 39 61
78 1812 5 24 true
100 JAVA FILL_UP MEDIUM 1670 19 81
79 3400 4 2 true
100 C_PLUS_PLUS FILL_UP LOW 1298 35 65
87 2139 25 20 true
100 DOT_NET PROGRAM LOW 277 22 78
76 1446 31 15 true
100 C_PLUS_PLUS PROGRAM HIGH 1412 2 98
89 1697 41 9 true
100 DOT_NET FILL_UP HIGH 394 20 80
84 2318 31 1 true
100 C_PLUS_PLUS MCQ LOW 934 90 10
72 1797 37 11 true
100 JAVA PROGRAM HIGH 1147 80 20
72 1579 45 22 true
100 C MCQ HIGH 668 30 70
82 157 32 13 true
100 JAVA MCQ MEDIUM 527 44 56
79 3814 27 5 true
100 DOT_NET PROGRAM MEDIUM 823 94 6
85 232 35 21 true
100 C FILL_UP HIGH 847 50 50
81 2960 23 2 true
100 JAVA MCQ HIGH 1621 28 72
70 2677 37 2 true
100 C FILL_UP MEDIUM 149 35 65
83 3612 28 16 true
100 C MCQ LOW 1602 41 59
88 646 30 11 true
100 JAVA FILL_UP HIGH 211 39 61
82 1949 29 14 true
100 C_PLUS_PLUS MCQ MEDIUM 986 22 78
85 3758 26 20 true
100 DOT_NET PROGRAM HIGH 1718 33 67
74 84 1 6 true
100 C_PLUS_PLUS MCQ MEDIUM 1402 81 19
79 4191 50 2 true
100 C PROGRAM LOW 268 68 32
82 3351 8 15 true
100 DOT_NET MCQ MEDIUM 348 32 68
72 2519 25 10 true
100 JAVA FILL_UP HIGH 1149 13 87
76 3211 6 10 true
100 C FILL_UP LOW 719 70 30
85 1536 29 2 true
100 DOT_NET FILL_UP HIGH 1070 81 19
77 2479 33 23 true
100 C MCQ LOW 889 36 64
88 2817 4 4 true
100 DOT_NET MCQ HIGH 1302 28 72
80 3270 35 5 true
100 C FILL_UP HIGH 53 51 49
83 925 18 3 true
100 C FILL_UP MEDIUM 973 48 52
85 1974 48 1 true
100 C PROGRAM LOW 54 0 100
77 4101 17 22 true
100 C_PLUS_PLUS MCQ MEDIUM 1046 53 47
87 51 16 20 true
100 C_PLUS_PLUS FILL_UP MEDIUM 314 56 44
72 1868 3 5 true
100 DOT_NET FILL_UP LOW 308 34 66
71 3141 42 25 true
100 DOT_NET PROGRAM MEDIUM 813 96 4
81 2156 4 14 true
100 C PROGRAM LOW 1259 21 79
88 1782 50 7 true
100 JAVA FILL_UP LOW 1222 39 61
73 754 37 17 true
100 C_PLUS_PLUS FILL_UP MEDIUM 155 82 18
85 3965 29 9 true
100 C_PLUS_PLUS MCQ MEDIUM 1770 75 25
77 2493 40 3 true
100 C MCQ HIGH 820 30 70
74 2152 22 11 true
100 C_PLUS_PLUS PROGRAM HIGH 1312 68 32
83 1430 4 2 true
100 DOT_NET MCQ LOW 829 81 19
74 3492 16 25 true
100 C_PLUS_PLUS FILL_UP HIGH 1247 86 14
77 2674 36 3 true
100 JAVA PROGRAM HIGH 1586 85 15
73 2267 48 5 true
100 DOT_NET MCQ HIGH 604 96 4
85 956 10 8 true
100 C_PLUS_PLUS PROGRAM LOW 576 70 30
85 3200 10 7 true
100 C PROGRAM MEDIUM 1759 51 49
88 791 39 14 true
100 C PROGRAM MEDIUM 1436 13 87
81 3293 48 12 true
100 DOT_NET PROGRAM HIGH 744 66 34
84 2800 1 23 true
100 DOT_NET PROGRAM LOW 1016 47 53
80 2243 5 8 true
100 C_PLUS_PLUS FILL_UP HIGH 1092 85 15
83 3011 21 17 true
100 C MCQ LOW 938 44 56
75 1996 46 17 true
100 DOT_NET PROGRAM MEDIUM 124 33 67
71 866 27 21 true
100 C_PLUS_PLUS PROGRAM HIGH 657 19 81
81 1958 25 1 true
100 JAVA PROGRAM HIGH 1219 33 67
84 3429 34 25 true
100 JAVA PROGRAM MEDIUM 490 46 54
84 2286 19 10 true
100 JAVA PROGRAM LOW 716 5 95
87 2560 19 6 true
100 DOT_NET MCQ HIGH 522 13 87
73 2093 5 5 true
100 JAVA PROGRAM HIGH 1510 29 71
78 854 9 17 true
100 C MCQ MEDIUM 1048 89 11
70 2934 4 24 true
100 JAVA FILL_UP HIGH 659 92 8
76 3617 43 23 true
100 C MCQ LOW 716 47 53
85 3943 32 2 true
100 DOT_NET FILL_UP LOW 330 2 98
81 3926 27 25 true
100 DOT_NET FILL_UP HIGH 1536 84 16
71 3826 41 13 true
100 C_PLUS_PLUS FILL_UP LOW 452 69 31
86 771 3 17 true
100 C_PLUS_PLUS MCQ MEDIUM 1094 85 15
84 2643 40 13 true
100 DOT_NET FILL_UP MEDIUM 1401 8 92
77 2946 21 12 true
100 C MCQ MEDIUM 1477 35 65
87 2660 47 11 true
100 DOT_NET MCQ LOW 1541 43 57
77 3696 41 9 true
100 JAVA PROGRAM HIGH 815 44 56
75 2610 39 9 true
100 C_PLUS_PLUS MCQ LOW 1379 78 22
87 1058 7 15 true
100 JAVA FILL_UP MEDIUM 920 21 79
71 2184 23 23 true
100 DOT_NET FILL_UP LOW 1281 28 72
70 348 26 11 true
100 C_PLUS_PLUS PROGRAM LOW 1155 11 89
75 3196 45 5 true
100 DOT_NET PROGRAM HIGH 1107 26 74
79 1627 17 21 true
100 DOT_NET FILL_UP LOW 821 0 100
76 2369 10 16 true
100 DOT_NET PROGRAM HIGH 839 70 30
70 3445 8 4 true
100 DOT_NET FILL_UP MEDIUM 363 50 50
87 716 49 22 true
100 C_PLUS_PLUS FILL_UP LOW 852 97 3
80 1539 17 5 true
100 C_PLUS_PLUS FILL_UP MEDIUM 1244 28 72
73 1914 9 22 true
100 C PROGRAM LOW 498 93 7
85 3379 11 16 true
100 C_PLUS_PLUS PROGRAM HIGH 1622 34 66
76 2124 50 10 true
100 DOT_NET MCQ MEDIUM 1695 87 13
71 2713 26 15 true
100 DOT_NET PROGRAM HIGH 963 28 72
87 819 23 18 true
100 C_PLUS_PLUS PROGRAM HIGH 363 3 97
70 3259 42 18 true
100 C_PLUS_PLUS FILL_UP HIGH 1708 27 73
72 3617 26 18 true
100 C_PLUS_PLUS FILL_UP MEDIUM 1082 99 1
84 183 31 10 true
100 C MCQ HIGH 368 80 20
73 3872 17 23 true
100 C PROGRAM MEDIUM 535 65 35
82 112 14 21 true
100 C MCQ LOW 609 18 82
79 2046 47 3 true
100 JAVA FILL_UP LOW 1789 49 51
83 1505 15 11 true
100 JAVA PROGRAM HIGH 1507 25 75
88 983 40 11 true
100 JAVA FILL_UP HIGH 596 84 16
71 1474 5 12 true
100 C_PLUS_PLUS FILL_UP HIGH 285 92 8
83 797 35 10 true
100 C PROGRAM HIGH 379 26 74
74 3400 41 17 true
100 JAVA FILL_UP HIGH 833 60 40
84 1321 24 13 true
4
JAVA 33
C 24
C_PLUS_PLUS 22
DOT_NET 47

//
Sample output of the URL:

Score of Sabareesh for
	JAVA is : 64
	C is : 69
	C_PLUS_PLUS is : 67
	DOT_NET is : 67

The Program is Executed for : 2 milliseconds
//

      
      

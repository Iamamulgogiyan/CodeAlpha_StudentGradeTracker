# CodeAlpha_StudentGradeTracker

A console-based **Student Grade Tracker** built in Core Java — CodeAlpha Java Development Internship, **Task 1**.

The program lets you store students' marks for 5 subjects and automatically calculates each student's **total, percentage and grade**, along with a **class summary (average, highest and lowest scores)**.

## ✨ Features

- Add a student with marks in 5 subjects (Java, DSA, DBMS, Operating System, Mathematics)
- Input validation — marks must be 0–100, duplicate roll numbers are rejected
- Full report of all students (total, percentage, grade)
- Class summary — total students, class average, highest scorer, lowest scorer
- Search a student by roll number (subject-wise detail view)
- Remove a student by roll number
- Crash-proof input handling (wrong input asks again instead of crashing)

## 🛠️ Concepts Used

- **OOP** — `Student` class with encapsulation (private fields + getters), objects stored in a collection
- **ArrayList** — dynamic storage for students (no fixed size)
- **Arrays** — subject marks per student
- **Loops & conditionals** — menu loop (`do-while`), `switch`, grade calculation
- **Exception handling** — `try-catch` for safe numeric input
- **String formatting** — neat tabular report using `printf`

## ▶️ How to Run

```bash
javac Student.java StudentGradeTracker.java
java StudentGradeTracker
```

## 📸 Sample Output

```
==============================================
          STUDENT GRADE TRACKER
==============================================

------------------ MENU ------------------
1. Add Student
2. Display All Students
3. Show Class Summary (Average / Highest / Lowest)
4. Search Student by Roll Number
5. Remove Student
6. Exit
------------------------------------------
```

Student Report:

```
=================== STUDENT REPORT ===================
Roll   Name                  Total  Percentage   Grade
------------------------------------------------------
101    Rahul Sharma            417       83.40%       A
102    Priya Singh             458       91.60%      A+
103    Amit Kumar              293       58.60%       D
======================================================
```

Class Summary:

```
=================== CLASS SUMMARY ====================
Total students : 3
Class average  : 77.87%
Highest scorer : Priya Singh (Roll 102) - 458 marks (91.60%)
Lowest scorer  : Amit Kumar (Roll 103) - 293 marks (58.60%)
======================================================
```

## 📂 Files

| File | Purpose |
|---|---|
| `Student.java` | Model class — stores one student's data and calculates total, percentage, grade |
| `StudentGradeTracker.java` | Main class — menu, add/display/summary/search/remove operations |

## 🔮 Future Improvements

- Save data to a file so records persist after the program closes
- GUI version using Java Swing/JavaFX
- MySQL database integration

---
*Made by Amul Gogyan — Java Development Intern @ CodeAlpha*

// ============================================================
//  Student.java
//  This class represents ONE student.
//  It stores the student's name, roll number and subject marks,
//  and can calculate total, percentage and grade.
// ============================================================

public class Student {

    // Fixed subjects - same for every student
    public static final String[] SUBJECTS = {"Java", "DSA", "DBMS", "Operating System", "Mathematics"};

    // Maximum marks in one subject
    public static final int MAX_MARKS_PER_SUBJECT = 100;

    // ---- Student's data (private = only this class can change it) ----
    private String name;
    private int rollNumber;
    private int[] marks;   // marks[0] = Java, marks[1] = DSA, and so on...

    // Constructor - runs when a new Student object is created
    public Student(String name, int rollNumber, int[] marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = marks;
    }

    // ---- Getters (to read the data from outside) ----
    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public int[] getMarks() {
        return marks;
    }

    // Total marks in all subjects
    public int getTotal() {
        int total = 0;
        for (int m : marks) {       // loop through every subject's marks
            total = total + m;
        }
        return total;
    }

    // Percentage = (obtained total / maximum total) x 100
    public double getPercentage() {
        int maximumTotal = SUBJECTS.length * MAX_MARKS_PER_SUBJECT;   // 5 x 100 = 500
        return (getTotal() * 100.0) / maximumTotal;
    }

    // Grade based on percentage
    public String getGrade() {
        double p = getPercentage();
        if (p >= 90) return "A+";
        if (p >= 80) return "A";
        if (p >= 70) return "B";
        if (p >= 60) return "C";
        if (p >= 50) return "D";
        return "F";
    }

    // One neat line showing this student's summary (used in the report table)
    @Override
    public String toString() {
        return String.format("%-6d %-20s %6d %11.2f%% %7s",
                rollNumber, name, getTotal(), getPercentage(), getGrade());
    }
}

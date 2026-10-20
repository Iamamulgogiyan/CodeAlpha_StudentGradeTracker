// ============================================================
//  StudentGradeTracker.java
//  CodeAlpha Internship - TASK 1 : Student Grade Tracker
//
//  A console-based program to:
//    * Add students with their subject marks
//    * Show a report of all students
//    * Calculate class average, highest and lowest scores
//    * Search and remove a student
//  Data is stored in an ArrayList<Student>.
// ============================================================

import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {

    // ArrayList stores all students. Unlike a normal array,
    // its size grows automatically as we add more students.
    private static ArrayList<Student> students = new ArrayList<>();

    // Scanner is used to take input from the keyboard
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("==============================================");
        System.out.println("          STUDENT GRADE TRACKER");
        System.out.println("==============================================");

        int choice;
        do {
            showMenu();
            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    displayAllStudents();
                    break;
                case 3:
                    showSummary();
                    break;
                case 4:
                    searchStudent();
                    break;
                case 5:
                    removeStudent();
                    break;
                case 6:
                    System.out.println("\nThank you for using Student Grade Tracker. Goodbye!");
                    break;
                default:
                    System.out.println("\nInvalid choice! Please enter a number from 1 to 6.");
            }
        } while (choice != 6);   // keep showing the menu until user chooses Exit
    }

    // ------------------------- MENU -------------------------
    private static void showMenu() {
        System.out.println("\n------------------ MENU ------------------");
        System.out.println("1. Add Student");
        System.out.println("2. Display All Students");
        System.out.println("3. Show Class Summary (Average / Highest / Lowest)");
        System.out.println("4. Search Student by Roll Number");
        System.out.println("5. Remove Student");
        System.out.println("6. Exit");
        System.out.println("------------------------------------------");
    }

    // ---------------------- ADD STUDENT ----------------------
    private static void addStudent() {
        System.out.println("\n--- Add New Student ---");

        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();

        int roll = readInt("Enter roll number: ");

        // A roll number must be unique - check if it already exists
        for (Student s : students) {
            if (s.getRollNumber() == roll) {
                System.out.println("A student with roll number " + roll + " already exists!");
                return;
            }
        }

        // Take marks for every subject, one by one
        int[] marks = new int[Student.SUBJECTS.length];
        for (int i = 0; i < Student.SUBJECTS.length; i++) {
            marks[i] = readInt("Enter marks in " + Student.SUBJECTS[i] + " (0-100): ");

            // Marks must be between 0 and 100 - keep asking until valid
            while (marks[i] < 0 || marks[i] > 100) {
                System.out.println("Invalid marks! Marks should be between 0 and 100.");
                marks[i] = readInt("Enter marks in " + Student.SUBJECTS[i] + " (0-100): ");
            }
        }

        // Create the Student object and store it in the ArrayList
        students.add(new Student(name, roll, marks));
        System.out.println("\nStudent added successfully!");
    }

    // ------------------- DISPLAY ALL STUDENTS -------------------
    private static void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("\nNo students found. Please add a student first.");
            return;
        }

        System.out.println("\n=================== STUDENT REPORT ===================");
        System.out.printf("%-6s %-20s %6s %12s %7s%n", "Roll", "Name", "Total", "Percentage", "Grade");
        System.out.println("------------------------------------------------------");
        for (Student s : students) {
            System.out.println(s);   // prints using Student's toString() method
        }
        System.out.println("======================================================");
    }

    // ---------------------- CLASS SUMMARY ----------------------
    private static void showSummary() {
        if (students.isEmpty()) {
            System.out.println("\nNo students found. Please add a student first.");
            return;
        }

        int totalOfAll = 0;
        Student highest = students.get(0);   // assume first student is highest, then compare
        Student lowest = students.get(0);    // assume first student is lowest, then compare

        for (Student s : students) {
            totalOfAll += s.getTotal();

            if (s.getTotal() > highest.getTotal()) {
                highest = s;
            }
            if (s.getTotal() < lowest.getTotal()) {
                lowest = s;
            }
        }

        // Average percentage of the whole class
        double classAverage = (totalOfAll * 100.0) / (students.size() * Student.SUBJECTS.length * Student.MAX_MARKS_PER_SUBJECT);

        System.out.println("\n=================== CLASS SUMMARY ====================");
        System.out.println("Total students : " + students.size());
        System.out.printf("Class average  : %.2f%%%n", classAverage);
        System.out.println("Highest scorer : " + highest.getName() + " (Roll " + highest.getRollNumber()
                + ") - " + highest.getTotal() + " marks (" + String.format("%.2f", highest.getPercentage()) + "%)");
        System.out.println("Lowest scorer  : " + lowest.getName() + " (Roll " + lowest.getRollNumber()
                + ") - " + lowest.getTotal() + " marks (" + String.format("%.2f", lowest.getPercentage()) + "%)");
        System.out.println("======================================================");
    }

    // ---------------------- SEARCH STUDENT ----------------------
    private static void searchStudent() {
        if (students.isEmpty()) {
            System.out.println("\nNo students found. Please add a student first.");
            return;
        }

        int roll = readInt("Enter roll number to search: ");

        for (Student s : students) {
            if (s.getRollNumber() == roll) {
                // Student found - show full subject-wise detail
                System.out.println("\n--- Student Found ---");
                System.out.println("Name       : " + s.getName());
                System.out.println("Roll Number: " + s.getRollNumber());
                int[] marks = s.getMarks();
                for (int i = 0; i < Student.SUBJECTS.length; i++) {
                    System.out.println("  " + Student.SUBJECTS[i] + ": " + marks[i]);
                }
                System.out.println("Total      : " + s.getTotal() + " / " + (Student.SUBJECTS.length * Student.MAX_MARKS_PER_SUBJECT));
                System.out.printf("Percentage : %.2f%%%n", s.getPercentage());
                System.out.println("Grade      : " + s.getGrade());
                return;
            }
        }
        System.out.println("No student found with roll number " + roll + ".");
    }

    // ---------------------- REMOVE STUDENT ----------------------
    private static void removeStudent() {
        if (students.isEmpty()) {
            System.out.println("\nNo students found. Nothing to remove.");
            return;
        }

        int roll = readInt("Enter roll number to remove: ");

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getRollNumber() == roll) {
                System.out.println("Removed: " + students.get(i).getName() + " (Roll " + roll + ")");
                students.remove(i);
                return;
            }
        }
        System.out.println("No student found with roll number " + roll + ".");
    }

    // ------------- HELPER: safely read an integer -------------
    // If the user types letters by mistake, we show a message
    // and ask again instead of crashing the program.
    private static int readInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}

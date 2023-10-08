/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ei;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
/**
 *
 * @author keerthana
 */




public class VirtualClassroomManager {
    private Map<String, Classroom> classrooms;
    private Map<String, Student> students;

    public VirtualClassroomManager() {
        classrooms = new HashMap<>();
        students = new HashMap<>();
    }

    // Method to add a classroom
    public void addClassroom(String className) {
        if (!classrooms.containsKey(className)) {
            Classroom classroom = new Classroom(className);
            classrooms.put(className, classroom);
            System.out.println("Classroom " + className + " has been created.");
            delay(1);
        } else {
            System.out.println("Classroom " + className + " already exists.");
            delay(1);
        }
    }

    // Method to add a student
    public void addStudent(String studentID, String studentName, String className) {
        if (students.containsKey(studentID)) {
            System.out.println("Student " + studentID + " already exists.");
            delay(1);
        } else {
            if (classrooms.containsKey(className)) {
                Classroom classroom = classrooms.get(className);
                Student student = new Student(studentID, studentName, classroom);
                classroom.addStudent(student);
                students.put(studentID, student);
                System.out.println("Student " + studentID + " has been enrolled in " + className + ".");
                delay(1);
            } else {
                System.out.println("Classroom " + className + " does not exist.");
                delay(1);
            }
        }
    }

    // Method to remove a classroom
    public void removeClassroom(String className) {
        if (classrooms.containsKey(className)) {
            classrooms.remove(className);
            System.out.println("Classroom " + className + " has been removed.");
            delay(1);
        } else {
            System.out.println("Classroom " + className + " does not exist.");
            delay(1);
        }
    }

    // Method to remove a student
    public void removeStudent(String studentID) {
        if (students.containsKey(studentID)) {
            Student student = students.get(studentID);
            Classroom classroom = student.getClassroom();
            classroom.removeStudent(student);
            students.remove(studentID);
            System.out.println("Student " + studentID + " has been removed.");
            delay(1);
        } else {
            System.out.println("Student " + studentID + " does not exist.");
            delay(1);
        }
    }

    // Method to schedule an assignment for a classroom
    public void scheduleAssignment(String className, String assignmentDetails) {
        if (classrooms.containsKey(className)) {
            Classroom classroom = classrooms.get(className);
            Assignment assignment = new Assignment(assignmentDetails);
            classroom.scheduleAssignment(assignment);
            System.out.println("Assignment for " + className + " has been scheduled.");
            delay(1);
        } else {
            System.out.println("Classroom " + className + " does not exist.");
            delay(1);
        }
    }

    // Method to submit an assignment by a student
    public void submitAssignment(String studentID, String className, String assignmentDetails) {
        if (students.containsKey(studentID) && classrooms.containsKey(className)) {
            Classroom classroom = classrooms.get(className);
            Assignment assignment = new Assignment(assignmentDetails);

            // Check if the student is enrolled in the specified classroom
            Student student = students.get(studentID);
            if (student.getClassroom() == classroom) {
                // Mark the assignment as submitted
                System.out.println("Assignment submitted by Student " + studentID + " in " + className + ".");
            } else {
                System.out.println("Student " + studentID + " is not enrolled in " + className + ".");
            }
        } else {
            System.out.println("Student " + studentID + " or classroom " + className + " does not exist.");
        }
    }

    // Method to list all classrooms
    public void listClassrooms() {
        System.out.println("List of Classrooms:");
        for (String className : classrooms.keySet()) {
            System.out.println(className);
        }
    }

    // Method to list students in a classroom
    public void listStudents(String className) {
        if (classrooms.containsKey(className)) {
            Classroom classroom = classrooms.get(className);
            List<Student> classroomStudents = classroom.getStudents();
            System.out.println("Students in " + className + ":");
            for (Student student : classroomStudents) {
                System.out.println(student.getName() + " (ID: " + student.getID() + ")");
            }
        } else {
            System.out.println("Classroom " + className + " does not exist.");
        }
    }

    // Method to list assignments for a classroom
    public void listAssignments(String className) {
        if (classrooms.containsKey(className)) {
            Classroom classroom = classrooms.get(className);
            List<Assignment> classroomAssignments = classroom.getAssignments();
            System.out.println("Assignments for " + className + ":");
            for (Assignment assignment : classroomAssignments) {
                System.out.println(assignment.getDetails());
            }
        } else {
            System.out.println("Classroom " + className + " does not exist.");
        }
    }
    public static void delay(int seconds) {
        try {
            Thread.sleep(seconds * 1000); // Sleep for the specified number of seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        VirtualClassroomManager manager = new VirtualClassroomManager();
        Scanner scanner = new Scanner(System.in);

        // Main program loop
        String[] menuOptions = {
    "Select an option:",
    "1. Add Classroom",
    "2. Add Student",
    "3. Schedule Assignment",
    "4. Submit Assignment",
    "5. Remove Classroom",
    "6. Remove Student",
    "7. List Classrooms",
    "8. List Students in Classroom",
    "9. List Assignments",
    "10.Exit",
};

while (true) {
    for (String option : menuOptions) {
        System.out.println(option);
        delay(1); // 1-second pause
    }
    System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter Classroom Name: ");
                    String className = scanner.nextLine();
                    manager.addClassroom(className);
                    System.out.println();
                    break;
                case 2:
                    System.out.print("Enter Student ID: ");
                    String studentID = scanner.nextLine();
                    System.out.print("Enter Student Name: ");
                    String studentName = scanner.nextLine();
                    System.out.print("Enter Classroom Name: ");
                    className = scanner.nextLine();
                    manager.addStudent(studentID, studentName, className);
                    System.out.println();
                    break;
               
                    
                case 3:
                    System.out.print("Enter Classroom Name to Schedule Assignment: ");
                    className = scanner.nextLine();
                    System.out.print("Enter Assignment Details: ");
                    String assignmentDetails = scanner.nextLine();
                    manager.scheduleAssignment(className, assignmentDetails);
                    System.out.println();
                    break;
                case 4 :
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextLine();
                    System.out.print("Enter Classroom Name: ");
                    className = scanner.nextLine();
                    System.out.print("Enter Assignment Details: ");
                    assignmentDetails = scanner.nextLine();
                    manager.submitAssignment(studentID, className, assignmentDetails);
                    System.out.println();
                    break;
                    case 5:
                    System.out.print("Enter Classroom Name to Remove: ");
                    className = scanner.nextLine();
                    manager.removeClassroom(className);
                    System.out.println();
                    break;
                case 6:
                    System.out.print("Enter Student ID to Remove: ");
                    studentID = scanner.nextLine();
                    manager.removeStudent(studentID);
                    System.out.println();
                    break;
                case 7:
                    manager.listClassrooms();
                    System.out.println();
                    break;
                case 8:
                    System.out.print("Enter Classroom Name to List Students: ");
                    className = scanner.nextLine();
                    manager.listStudents(className);
                    System.out.println();
                    break;
                case 9:
                    System.out.print("Enter Classroom Name to List Assignments: ");
                    className = scanner.nextLine();
                    manager.listAssignments(className);
                    System.out.println();
                    break;
                case 10:
                    System.out.println("Exiting.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
    }
}

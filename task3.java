import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class Student {
    private String n;
    private int rn;
    private String g;

    public Student(String n, int rn, String g) {
        this.n = n;
        this.rn = rn;
        this.g = g;
    }

    public String getn() {
        return n;
    }

    public int getRollNumber() {
        return rn;
    }

    public String getGrade() {
        return g;
    }

    @Override
    public String toString() {
        return "Name is " + n + ", Roll Number is " + rn + ", Grade is " + g;
    }
}

class StudentManagementSystem {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(int rn) {
        students.removeIf(student -> student.getRollNumber() == rn);
    }

    public Student findStudent(int rn) {
        for (Student student : students) {
            if (student.getRollNumber() == rn) {
                return student;
            }
        }
        return null;
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public void saveToFile(String filen) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filen))) {
            oos.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile(String filen) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filen))) {
            students = (List<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

public class task3 {
    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Student Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Save to File");
            System.out.println("6. Load from File");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter n: ");
                    String n = scanner.nextLine();
                    System.out.print("Enter roll number: ");
                    int rn = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter g: ");
                    String g = scanner.nextLine();

                    Student student = new Student(n, rn, g);
                    sms.addStudent(student);
                    break;
                case 2:
                    System.out.print("Enter roll number to remove: ");
                    int rollToRemove = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    sms.removeStudent(rollToRemove);
                    break;
                case 3:
                    System.out.print("Enter roll number to search: ");
                    int rollToSearch = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    Student foundStudent = sms.findStudent(rollToSearch);
                    if (foundStudent != null) {
                        System.out.println("Student found: " + foundStudent);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    List<Student> allStudents = sms.getAllStudents();
                    if (allStudents.isEmpty()) {
                        System.out.println("No students in the system.");
                    } else {
                        for (Student s : allStudents) {
                            System.out.println(s);
                        }
                    }
                    break;
                case 5:
                    sms.saveToFile("students.dat");
                    System.out.println("Data saved.");
                    break;
                case 6:
                    sms.loadFromFile("students.dat");
                    System.out.println("Data loaded.");
                    break;
                case 7:
                    System.out.println("Exit.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("......Invalid option.....\nTry again.");
            }
        }
    }
}
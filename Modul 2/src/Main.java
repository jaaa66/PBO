import java.util.ArrayList;
import java.util.Scanner;

// Class Student
class Student {
    String name;
    String faculty;
    String programStudi;

    // Constructor
    public Student(String name, String faculty, String programStudi) {
        this.name = name;
        this.faculty = faculty;
        this.programStudi = programStudi;
    }

    // Method to display books
    public void displayBooks(ArrayList<String> bookList) {
        System.out.println("Daftar Buku:");
        for (String book : bookList) {
            System.out.println(book);
        }
    }

    // Method to logout
    public void logout() {
        System.out.println("System Logout...");
    }
}

// Class Admin
class Admin {
    String adminUsername = "admin";
    String adminPassword = "admin";
    ArrayList<Student> studentList = new ArrayList<>();

    // Method to add student
    public void addStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student faculty: ");
        String faculty = scanner.nextLine();
        System.out.print("Enter student program: ");
        String programStudi = scanner.nextLine();
        System.out.print("Enter student NIM: ");
        String nim = scanner.nextLine();

        if (nim.length() != 15) {
            System.out.println("NIM harus 15 digit");
            return;
        }

        Student student = new Student(name, faculty, programStudi);
        studentList.add(student);
        System.out.println("Student successfully registered.");
    }

    // Method to display students
    public void displayStudents() {
        System.out.println("List of Registered Students:");
        for (Student student : studentList) {
            System.out.println("Name: " + student.name);
            System.out.println("Faculty: " + student.faculty);
            System.out.println("Program: " + student.programStudi);
        }
    }
}

// Class Main
public class Main {
    ArrayList<String> bookList = new ArrayList<>();
    ArrayList<String> userStudent = new ArrayList<>();

    // Method to display menu
    public void Menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("===== Library System =====");
            System.out.println("1. Login as Student");
            System.out.println("2. Login as Admin");
            System.out.println("3. Exit");
            System.out.print("Choose option (1-3): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    menuStudent();
                    break;
                case 2:
                    menuAdmin();
                    break;
                case 3:
                    System.out.println("Thank you. Exiting program.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Method for student input
    public void inputNim() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your NIM (input 99 untuk back): ");
        String nim = scanner.nextLine();
        if (nim.equals("99")) {
            return;
        }

        boolean found = checkNim(nim);
        if (found) {
            menuStudent();
        } else {
            System.out.println("NIM tidak ditemukan.");
        }
    }

    // Method to check NIM
    public boolean checkNim(String nim) {
        return userStudent.contains(nim);
    }

    // Method for admin menu
    public void menuAdmin() {
        Admin admin = new Admin();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username (admin): ");
        String username = scanner.nextLine();
        System.out.print("Enter your password (admin): ");
        String password = scanner.nextLine();

        if (username.equals(admin.adminUsername) && password.equals(admin.adminPassword)) {
            while (true) {
                System.out.println("===== Admin Menu =====");
                System.out.println("1. Add Student");
                System.out.println("2. Display Registered Students");
                System.out.println("3. Logout");
                System.out.print("Choose option (1-3): ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        admin.addStudent();
                        break;
                    case 2:
                        admin.displayStudents();
                        break;
                    case 3:
                        System.out.println("Logging out from admin account.");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid credentials for admin.");
        }
    }

    // Method for student menu
    public void menuStudent() {
        Student student = new Student("", "", "");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("===== Student Menu =====");
            System.out.println("1. Buku terpinjam");
            System.out.println("2. Pinjam buku");
            System.out.println("3. Pinjam Buku atau Logout");
            System.out.print("Choose option (1-3): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    student.displayBooks(bookList);
                    break;
                case 2:
                    System.out.println("Pinjam buku belum diimplementasikan.");
                    break;
                case 3:
                    student.logout();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.bookList.add("1 || 388C-c681-9152 || title || author || Sejarah || 4");
        main.bookList.add("2 || ed9b-be3a-5cdb || title || author || Sejarah || 0");
        main.bookList.add("3 || d95e-0c4a-9523 || title || author || Sejarah || 2");

        main.userStudent.add("202210370311203");
        main.userStudent.add("2095103701521");

        main.Menu();
    }
}

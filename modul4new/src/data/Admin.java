package data;

import books.Book;
import util.iMenu;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Scanner;

public class Admin extends User implements iMenu {
    private String adminUsername;
    private String adminPassword;
    private List<Student> studentList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public Admin(String adminUsername, String adminPassword) {
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
    }

    public boolean isAdmin(String username, String password) {
        return username.equals(adminUsername) && password.equals(adminPassword);
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    @Override
    public void menu() {
        boolean adminLoggedIn = true;
        while (adminLoggedIn) {
            System.out.println("===== Admin Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. Add Book");
            System.out.println("3. Display Registered Students");
            System.out.println("4. Display Available Books");
            System.out.println("5. Logout");
            System.out.print("Choose option (1-5): ");
            int adminChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (adminChoice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    inputBook();
                    break;
                case 3:
                    displayStudents();
                    break;
                case 4:
                    displayBooks();
                    break;
                case 5:
                    System.out.println("Logging out from admin account.");
                    adminLoggedIn = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student NIM: ");
        String nim = scanner.nextLine();
        if (nim.length() != 15) {
            System.out.println("NIM must be 15 digits.");
            return;
        }
        System.out.print("Enter student faculty: ");
        String faculty = scanner.nextLine();
        System.out.print("Enter student program: ");
        String program = scanner.nextLine();
        Student student = new Student(name, nim, faculty, program);
        studentList.add(student);
        System.out.println("Student successfully registered.");
    }

    public void inputBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Select book category: 1. History 2. Story 3. Text ");
        int categoryChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String category = "";
        switch (categoryChoice) {
            case 1:
                category = "History";
                break;
            case 2:
                category = "Story";
                break;
            case 3:
                category = "Text";
                break;
            default:
                System.out.println("Invalid category.");
                return;
        }

        System.out.print("Enter the stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String bookId = UUID.randomUUID().toString();
        Book book = new Book(bookId, title, author, category, stock, 0);
        addBook(book);
        System.out.println("Book successfully added to the library.");
    }

    public void displayStudents() {
        System.out.println("List of Registered Students:");
        for (Student student : studentList) {
            System.out.println("Name: " + student.getName() + ", Faculty: " + student.getFaculty() + ", NIM: " + student.getNim() + ", Program: " + student.getProgramStudi());
        }
    }
}


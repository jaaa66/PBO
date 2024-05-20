package com.main;

import data.Admin;
import data.Student;
import books.Book;
import books.HistoryBook;
import books.StoryBook;
import books.TextBook;
import java.util.Scanner;

public class LibrarySystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin("admin", "password");

        while (true) {
            System.out.println("===== Library System =====");
            System.out.println("1. Login as Student");
            System.out.println("2. Login as Admin");
            System.out.println("3. Exit");
            System.out.print("Choose option (1-3): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.print("Enter your NIM (input 99 untuk back): ");
                String nim = scanner.nextLine();
                if (nim.equals("99")) continue;

                Student student = null;
                for (Student s : admin.getStudentList()) {
                    if (s.getNim().equals(nim)) {
                        student = s;
                        break;
                    }
                }
                if (student == null) {
                    System.out.println("NIM not found.");
                    continue;
                }

                student.menu();
            } else if (choice == 2) {
                System.out.print("Enter your username (admin): ");
                String username = scanner.nextLine();
                System.out.print("Enter your password (admin): ");
                String password = scanner.nextLine();

                if (!admin.isAdmin(username, password)) {
                    System.out.println("Invalid credentials. Please try again.");
                    continue;
                }

                admin.menu();
            } else if (choice == 3) {
                System.out.println("Thank you. Exiting program.");
                break;
            } else {
                System.out.println("Invalid choice. Please select again.");
            }
        }
        scanner.close();
    }
}

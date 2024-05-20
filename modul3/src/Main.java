import java.util.Scanner;

public class Main {
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
                for (Student s : admin.getStudents()) {
                    if (s.getNim().equals(nim)) {
                        student = s;
                        break;
                    }
                }
                if (student == null) {
                    System.out.println("NIM not found.");
                    continue;
                }

                boolean studentLoggedIn = true;
                while (studentLoggedIn) {
                    System.out.println("==== Student Menu ====");
                    System.out.println("1. Buku terpinjam");
                    System.out.println("2. Pinjam buku");
                    System.out.println("3. Kembalikan buku");
                    System.out.println("4. Pinjam Buku atau Logout");
                    System.out.print("Choose option (1-4): ");
                    int studentChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (studentChoice) {
                        case 1:
                            if (student.getBorrowedBooks().isEmpty()) {
                                System.out.println("Belum ada buku yang dipilih. Silahkan pilih buku terlebih dahulu.");
                            } else {
                                student.showBorrowedBooks();
                            }
                            break;
                        case 2:
                            admin.displayBooks();
                            System.out.print("Input Id buku yang ingin dipinjam (input 99 untuk back): ");
                            String bookId = scanner.nextLine();
                            if (bookId.equals("99")) break;

                            Book bookToBorrow = null;
                            for (Book b : admin.getBooks()) {
                                if (b.getBookId().equals(bookId)) {
                                    bookToBorrow = b;
                                    break;
                                }
                            }

                            if (bookToBorrow == null) {
                                System.out.println("Book ID not found.");
                                break;
                            }

                            if (bookToBorrow.getStock() == 0) {
                                System.out.println("Stock buku kosong! Silahkan pilih yang lain.");
                                break;
                            }

                            System.out.print("Berapa lama buku akan dipinjam? (maksimal 14 hari) Input lama (hari): ");
                            int days = scanner.nextInt();
                            scanner.nextLine(); // Consume newline

                            student.borrowBook(bookToBorrow, days);
                            break;
                        case 3:
                            student.showBorrowedBooks();
                            System.out.print("Input Id buku yang ingin dikembalikan (input 99 untuk back): ");
                            String returnBookId = scanner.nextLine();
                            if (returnBookId.equals("99")) break;

                            Book bookToReturn = null;
                            for (Book b : student.getBorrowedBooks()) {
                                if (b.getBookId().equals(returnBookId)) {
                                    bookToReturn = b;
                                    break;
                                }
                            }

                            if (bookToReturn == null) {
                                System.out.println("Book ID not found in borrowed books.");
                                break;
                            }

                            student.returnBooks(bookToReturn);
                            break;
                        case 4:
                            student.logout();
                            studentLoggedIn = false;
                            break;
                        default:
                            System.out.println("Invalid option.");
                    }
                }
            } else if (choice == 2) {
                System.out.print("Enter your username (admin): ");
                String username = scanner.nextLine();
                System.out.print("Enter your password (admin): ");
                String password = scanner.nextLine();

                if (!admin.isAdmin(username, password)) {
                    System.out.println("Invalid credentials. Please try again.");
                    continue;
                }

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
                            System.out.print("Enter student name: ");
                            String name = scanner.nextLine();
                            System.out.print("Enter student NIM: ");
                            String nim = scanner.nextLine();
                            if (nim.length() != 15) {
                                System.out.println("NIM must be 15 digits.");
                                break;
                            }
                            System.out.print("Enter student faculty: ");
                            String faculty = scanner.nextLine();
                            System.out.print("Enter student program: ");
                            String program = scanner.nextLine();
                            Student student = new Student(name, nim, faculty, program);
                            admin.addStudent(student);
                            break;
                        case 2:
                            System.out.print("Enter book title: ");
                            String title = scanner.nextLine();
                            System.out.print("Enter author: ");
                            String author = scanner.nextLine();
                            System.out.print("Enter book category: ");
                            String category = scanner.nextLine();
                            System.out.print("Enter the stock: ");
                            int stock = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            String bookId = admin.generateId();
                            Book book = new Book(bookId, title, author, category, stock, 0);
                            admin.inputBook(book);
                            break;
                        case 3:
                            admin.displayStudents();
                            break;
                        case 4:
                            admin.displayBooks();
                            break;
                        case 5:
                            System.out.println("Logging out from admin account.");
                            adminLoggedIn = false;
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                }
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

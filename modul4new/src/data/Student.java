package data;

import books.Book;
import util.iMenu;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student extends User implements iMenu {
    private String name;
    private String nim;
    private String faculty;
    private String programStudi;
    private List<Book> borrowedBooks = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public Student(String name, String nim, String faculty, String programStudi) {
        this.name = name;
        this.nim = nim;
        this.faculty = faculty;
        this.programStudi = programStudi;
    }

    public String getName() {
        return name;
    }

    public String getNim() {
        return nim;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getProgramStudi() {
        return programStudi;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("NIM: " + nim);
        System.out.println("Faculty: " + faculty);
        System.out.println("Program Studi: " + programStudi);
    }

    @Override
    public void menu() {
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
                    if (borrowedBooks.isEmpty()) {
                        System.out.println("Belum ada buku yang dipilih. Silahkan pilih buku terlebih dahulu.");
                    } else {
                        showBorrowedBooks();
                    }
                    break;
                case 2:
                    choiceBook();
                    break;
                case 3:
                    showBorrowedBooks();
                    System.out.print("Input Id buku yang ingin dikembalikan (input 99 untuk back): ");
                    String returnBookId = scanner.nextLine();
                    if (returnBookId.equals("99")) break;

                    Book bookToReturn = null;
                    for (Book b : borrowedBooks) {
                        if (b.getBookId().equals(returnBookId)) {
                            bookToReturn = b;
                            break;
                        }
                    }

                    if (bookToReturn == null) {
                        System.out.println("Book ID not found in borrowed books.");
                        break;
                    }

                    returnBooks(bookToReturn);
                    break;
                case 4:
                    logout();
                    studentLoggedIn = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public void showBorrowedBooks() {
        System.out.println("=================================================================================");
        System.out.println(" No.  Id buku                Nama Buku     Author        Category    Durasi");
        System.out.println("=================================================================================");
        int no = 1;
        for (Book book : borrowedBooks) {
            System.out.printf(" %-4d %-21s %-12s %-12s %-10s %-5d\n", no++, book.getBookId(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getDuration());
        }
        System.out.println("=================================================================================");
    }

    public void choiceBook() {
        displayBooks();
        System.out.print("Input Id buku yang ingin dipinjam (input 99 untuk back): ");
        String bookId = scanner.nextLine();
        if (bookId.equals("99")) return;

        Book bookToBorrow = null;
        for (Book b : bookList) {
            if (b.getBookId().equals(bookId)) {
                bookToBorrow = b;
                break;
            }
        }

        if (bookToBorrow == null) {
            System.out.println("Book ID not found.");
            return;
        }

        if (bookToBorrow.getStock() == 0) {
            System.out.println("Stock buku kosong! Silahkan pilih yang lain.");
            return;
        }

        System.out.print("Berapa lama buku akan dipinjam? (maksimal 14 hari) ");
        int duration = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (duration > 14) {
            System.out.println("Durasi peminjaman maksimal 14 hari.");
            return;
        }

        bookToBorrow.setDuration(duration);
        borrowedBooks.add(bookToBorrow);
        bookToBorrow.setStock(bookToBorrow.getStock() - 1);
        System.out.println("Buku berhasil dipinjam.");
    }

    public void returnBooks(Book book) {
        borrowedBooks.remove(book);
        book.setStock(book.getStock() + 1);
        System.out.println("Buku berhasil dikembalikan.");
    }

    public void logout() {
        System.out.println("Logout berhasil.");
    }
}

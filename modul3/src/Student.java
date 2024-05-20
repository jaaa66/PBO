import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private String name;
    private String nim;
    private String faculty;
    private String programStudi;
    private List<Book> borrowedBooks = new ArrayList<>();

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

    public void logout() {
        System.out.println("Student logged out.");
    }

    @Override
    public void displayBooks() {
        super.displayBooks();
    }

    public void returnBooks(Book book) {
        borrowedBooks.remove(book);
        book.setStock(book.getStock() + 1);
        System.out.println("Book returned: " + book.getTitle());
    }

    public void borrowBook(Book book, int days) {
        if (book.getStock() > 0) {
            book.setDuration(days);
            borrowedBooks.add(book);
            book.setStock(book.getStock() - 1);
            System.out.println("Book borrowed: " + book.getTitle() + " for " + days + " days.");
        } else {
            System.out.println("Book out of stock: " + book.getTitle());
        }
    }
}

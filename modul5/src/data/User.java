package data;

import books.Book;
import java.util.ArrayList;
import java.util.List;

public class User {
    protected List<Book> bookList = new ArrayList<>();

    public void displayBooks() {
        System.out.println("=================================================================================");
        System.out.println(" No.  Id buku                Nama Buku     Author        Category    Stock");
        System.out.println("=================================================================================");
        int no = 1;
        for (Book book : bookList) {
            System.out.printf(" %-4d %-21s %-12s %-12s %-10s %-5d\n", no++, book.getBookId(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getStock());
        }
        System.out.println("=================================================================================");
    }

    public void addBook(Book book) {
        bookList.add(book);
    }

    public List<Book> getBookList() {
        return bookList;
    }
}

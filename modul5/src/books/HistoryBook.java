package books;

public class HistoryBook extends Book {
    public HistoryBook(String bookId, String title, String author, int stock, int duration) {
        super(bookId, title, author, "History", stock, duration);
    }
}

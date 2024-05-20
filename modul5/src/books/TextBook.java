package books;

public class TextBook extends Book {
    public TextBook(String bookId, String title, String author, int stock, int duration) {
        super(bookId, title, author, "Text", stock, duration);
    }
}


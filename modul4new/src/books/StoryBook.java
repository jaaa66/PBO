package books;

public class StoryBook extends Book {
    public StoryBook(String bookId, String title, String author, int stock, int duration) {
        super(bookId, title, author, "Story", stock, duration);
    }
}

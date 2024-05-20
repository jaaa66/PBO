import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Admin extends User {
    private List<Student> studentList = new ArrayList<>();
    private String adminUsername;
    private String adminPassword;

    public Admin(String adminUsername, String adminPassword) {
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
    }

    public void addStudent(Student student) {
        studentList.add(student);
        System.out.println("Student successfully registered.");
    }

    public void inputBook(Book book) {
        addBook(book);
        System.out.println("Book successfully added to the library.");
    }

    @Override
    public void displayBooks() {
        super.displayBooks();
    }

    public void displayStudents() {
        System.out.println("List of Registered Students:");
        for (Student student : studentList) {
            System.out.println("Name: " + student.getName() + " Faculty: " + student.getFaculty() + " NIM: " + student.getNim() + " Program: " + student.getProgramStudi());
        }
    }

    public boolean isAdmin(String username, String password) {
        return username.equals(adminUsername) && password.equals(adminPassword);
    }

    public String generateId() {
        return UUID.randomUUID().toString();
    }

    public List<Student> getStudents() {
        return studentList;
    }

    public List<Book> getBooks() {
        return bookList;
    }
}

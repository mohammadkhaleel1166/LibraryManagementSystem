import java.util.ArrayList;
import java.util.List;

public class Patron {
    private int id;
    private String name;
    private List<Book> borrowedBooks;

    public Patron(int id, String name) {
        this.id = id;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public Book returnBookByISBN(String ISBN) {
        for (Book book : borrowedBooks) {
            if (book.getISBN().equals(ISBN)) {
                borrowedBooks.remove(book);
                return book;
            }
        }
        return null; // Book not found or not borrowed by this patron
    }

    public void displayPatronInfo() {
        System.out.println("Patron ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Borrowed Books:");
        if (borrowedBooks.isEmpty()) {
            System.out.println("No books borrowed.");
        } else {
            for (Book book : borrowedBooks) {
                book.displayBookInfo();
            }
        }
    }
}

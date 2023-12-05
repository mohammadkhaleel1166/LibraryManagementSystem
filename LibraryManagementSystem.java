import java.util.*;

public class LibraryManagementSystem {
    private List<Book> bookCollection;
    private List<Patron> patronList;
    private Scanner scanner;

    public LibraryManagementSystem() {
        this.bookCollection = new ArrayList<>();
        this.patronList = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // Book management functions
    public void addBook(Book book) {
        bookCollection.add(book);
    }

    public void removeBook(String ISBN) {
        for (Book book : bookCollection) {
            if (book.getISBN().equals(ISBN)) {
                bookCollection.remove(book);
                System.out.println("Book removed successfully.");
                return;
            }
        }
        System.out.println("Book with ISBN " + ISBN + " not found.");
    }

    public void displayAvailableBooks() {
        if (bookCollection.isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (Book book : bookCollection) {
                book.displayBookInfo();
            }
        }
    }

    // Patron management functions
    public void addPatron(Patron patron) {
        patronList.add(patron);
    }

    public void removePatron(int id) {
        for (Patron patron : patronList) {
            if (patron.getId() == id) {
                patronList.remove(patron);
                System.out.println("Patron removed successfully.");
                return;
            }
        }
        System.out.println("Patron with ID " + id + " not found.");
    }

    public void displayPatrons() {
        if (patronList.isEmpty()) {
            System.out.println("No patrons available.");
        } else {
            for (Patron patron : patronList) {
                patron.displayPatronInfo();
            }
        }
    }

    // Borrow and return book functions
    public void borrowBook(int patronId, String bookISBN) {
       
        Patron patron = findPatronById(patronId);
        if (patron == null) {
            System.out.println("Patron with ID " + patronId + " not found.");
            return;
        }

        Book book = findBookByISBN(bookISBN);
        if (book == null) {
            System.out.println("Book with ISBN " + bookISBN + " not found.");
            return;
        }

        if (book.getQuantity() > 0) {
            book.setQuantity(book.getQuantity() - 1);
            patron.borrowBook(book);
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Book is out of stock.");
        }
    }

    public void returnBook(int patronId, String bookISBN) {
        // logic for returning a book
        Patron patron = findPatronById(patronId);
        if (patron == null) {
            System.out.println("Patron with ID " + patronId + " not found.");
            return;
        }

        Book returnBook = patron.returnBookByISBN(bookISBN);
        if (returnBook != null) {
            returnBook.setQuantity(returnBook.getQuantity() + 1);
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Book with ISBN " + bookISBN + " not borrowed by patron.");
        }
    }

    private Patron findPatronById(int patronId) {
        for (Patron patron : patronList) {
            if (patron.getId() == patronId) {
                return patron;
            }
        }
        return null;
    }

    private Book findBookByISBN(String bookISBN) {
        for (Book book : bookCollection) {
            if (book.getISBN().equals(bookISBN)) {
                return book;
            }
        }
        return null;
    }

    // Adding a new book method
    public void addNewBook() {
        System.out.println("Enter the title of the book:");
        String title = scanner.nextLine();
        System.out.println("Enter the author of the book:");
        String author = scanner.nextLine();
        System.out.println("Enter the ISBN of the book:");
        String isbn = scanner.nextLine();
        System.out.println("Enter the quantity of the book:");
        int quantity = scanner.nextInt();
        scanner.nextLine(); 
        System.out.println("Enter the type of book (Fiction or Non-Fiction):");
    String bookType = scanner.nextLine();

    if (bookType.equalsIgnoreCase("Fiction")) {
        System.out.println("Enter the genre of the fiction book:");
        String genre = scanner.nextLine();
        Book newBook = new FictionBook(title, author, isbn, quantity, genre);
        addBook(newBook);
        System.out.println("Fiction Book added successfully.");
    } else if (bookType.equalsIgnoreCase("Non-Fiction")) {
        System.out.println("Enter the subject of the non-fiction book:");
        String subject = scanner.nextLine();
        Book newBook = new NonFictionBook(title, author, isbn, quantity, subject);
        addBook(newBook);
        System.out.println("Non-Fiction Book added successfully.");
    } else {
        System.out.println("Invalid book type.");
    }
    }


   
    public void startApplication() {
        System.out.println("\t\tWelcome to the Library Management System");
        System.out.println("\t\t-----------------------------------------");
        int choice = -1;
        int x=0;
        while (choice != 9) {
            System.out.println();
            displayMenu();
            
            choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            switch (choice) {
                case 1:
                    addNewBook();
                    break;
                case 2:
                    System.out.println("Enter the ISBN of the book to remove:");
                    String isbn = scanner.nextLine();
                    removeBook(isbn);
                    break;
                case 3:
                    displayAvailableBooks();
                    break;
                case 4:
                    System.out.println("Enter the patron name:");
                    String name = scanner.nextLine();
                    Patron newPatron = new Patron(x++, name);
                    addPatron(newPatron);
                    System.out.println("Patron added successfully.");
                    break;
                case 5:
                    System.out.println("Enter the ID of the patron to remove:");
                    int id = scanner.nextInt();
                    removePatron(id);
                    break;
                case 6:
                    displayPatrons();
                    break;
                case 7:
                    System.out.println("Enter the patron ID:");
                    int patronId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter the book ISBN to borrow:");
                    String bookIsbn = scanner.nextLine();
                    borrowBook(patronId, bookIsbn);
                    break;
                case 8:
                    System.out.println("Enter the patron ID:");
                    int returnPatronId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter the book ISBN to return:");
                    String returnBookIsbn = scanner.nextLine();
                    returnBook(returnPatronId, returnBookIsbn);
                    break;
                case 9:
                    System.out.println("Exiting the Library Management System.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }

    private void displayMenu() {
        System.out.println("1. Add a new book");
        System.out.println("2. Remove a book");
        System.out.println("3. Display available books");
        System.out.println("4. Add a new patron");
        System.out.println("5. Remove a patron");
        System.out.println("6. Display patrons");
        System.out.println("7. Borrow a book");
        System.out.println("8. Return a book");
        System.out.println("9. Exit");
        System.out.println("Enter your choice:");
    }

    public static void main(String[] args) {
        LibraryManagementSystem librarySystem = new LibraryManagementSystem();
        librarySystem.startApplication();
    }
}

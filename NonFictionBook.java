public class NonFictionBook extends Book {
    private String subject;

    public NonFictionBook(String title, String author, String isbn, int quantity, String subject) {
        super(title, author, isbn, quantity);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public void displayBookInfo() {
        super.displayBookInfo();
        System.out.println("Subject: " + subject);
        System.out.println();
    }
}
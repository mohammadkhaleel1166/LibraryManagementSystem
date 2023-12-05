public class FictionBook extends Book {
    private String genre;

    public FictionBook(String title, String author, String isbn, int quantity, String genre) {
        super(title, author, isbn, quantity);
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public void displayBookInfo() {
        super.displayBookInfo();
        System.out.println("Genre: " + genre);
        System.out.println();
    }
}
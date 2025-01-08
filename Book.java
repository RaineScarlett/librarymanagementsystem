public class Book {
    private String title;
    private String author;
    private String genre;
    private Shelf shelf;  // Book knows only about the Shelf

    public Book(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public Shelf getShelf() {
        return shelf;
    }

    // Set shelf for book
    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }

    // Get the floor from the shelf
    public Floor getFloor() {
        return shelf != null ? shelf.getFloor() : null;
    }

    @Override
    public String toString() {
        return title + " by " + author + " (Genre: " + genre + ", Shelf: " + shelf.getName() + ", Floor: " + getFloor().getName() + ")";
    }
}
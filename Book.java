public class Book {
    private String title;
    private String author;
    private String genre;
    private Shelf shelf;  // Reference to the shelf the book is on

    // Constructor to create a book
    public Book(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    // Getter for title
    public String getTitle() {
        return title;
    }

    // Getter for author
    public String getAuthor() {
        return author;
    }

    // Getter for genre
    public String getGenre() {
        return genre;
    }

    // Setter for shelf (to associate a book with a shelf)
    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }

    // Getter for shelf
    public Shelf getShelf() {
        return shelf;
    }

    // Overriding toString() to include shelf and floor details
    @Override
    public String toString() {
        String shelfName = (shelf != null) ? shelf.getName() : "No shelf assigned";
        String floorName = (shelf != null && shelf.getFloor() != null) ? shelf.getFloor().getName() : "No floor assigned";
        return "Book: " + title + ", Author: " + author + ", Genre: " + genre + ", Shelf: " + shelfName + ", Floor: " + floorName;
    }
}
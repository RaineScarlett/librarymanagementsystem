import java.util.ArrayList;
import java.util.List;

public class Shelf extends Observable {
    private String name;
    private Floor floor;  // Reference to the floor the shelf is on
    private List<Book> books;

    public Shelf(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    // Set the floor for this shelf
    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    // Get the floor of this shelf
    public Floor getFloor() {
        return floor;
    }

    public String getName() {
        return name;
    }

    public void addBook(Book book) {
        books.add(book);
        book.setShelf(this);
        notifyObservers("New book added: " + book.getTitle() + " to " + name);
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return name;
    }
}
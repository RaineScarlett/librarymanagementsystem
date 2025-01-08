import java.util.ArrayList;
import java.util.List;

public class Floor {
    private String name;
    private List<Shelf> shelves;

    public Floor(String name) {
        this.name = name;
        this.shelves = new ArrayList<>();
    }

    public void addShelf(Shelf shelf) {
        shelf.setFloor(this);  // Set the floor for the shelf
        shelves.add(shelf);
    }

    public List<Book> getAllBooks() {
        List<Book> allBooks = new ArrayList<>();
        for (Shelf shelf : shelves) {
            allBooks.addAll(shelf.getBooks());
        }
        return allBooks;
    }

    public String getName() {
        return name;
    }
}
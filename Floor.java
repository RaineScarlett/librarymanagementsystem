import java.util.ArrayList;
import java.util.List;

public class Floor {
    private String name;
    private List<Shelf> shelves;

    public Floor(String name) {
        this.name = name;
        this.shelves = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addShelf(Shelf shelf) {
        shelves.add(shelf);
    }

    public List<Shelf> getShelves() {
        return shelves;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        for (Shelf shelf : shelves) {
            books.addAll(shelf.getBooks());
        }
        return books;
    }
}
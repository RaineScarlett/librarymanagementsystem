import java.util.ArrayList;
import java.util.List;

public class Shelf {
    private String name;
    private Floor floor;  // Shelf knows which floor it belongs to
    private List<Book> books;

    public Shelf(String name, Floor floor) {
        this.name = name;
        this.floor = floor;
        this.books = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Floor getFloor() {
        return floor;
    }

    public void addBook(Book book) {
        books.add(book);
        book.setShelf(this);  // Associate the book with this shelf
    }

    public List<Book> getBooks() {
        return books;
    }
}
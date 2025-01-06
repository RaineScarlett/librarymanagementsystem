import java.util.List;

public abstract class BaseSearchStrategy implements SearchStrategy {
    protected List<Book> books;
    public BaseSearchStrategy(List<Book> books) {
        this.books = books;
    }
}

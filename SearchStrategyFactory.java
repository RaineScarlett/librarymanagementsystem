import java.util.List;

public class SearchStrategyFactory {
    private List<Book> books;

    public SearchStrategyFactory(List<Book> books) {
        this.books = books;
    }

    public SearchStrategy createSearchStrategy(int choice) {
        switch (choice) {
            case 1:
                return new SearchByTitle(books);
            case 2:
                return new SearchByAuthor(books);
            case 3:
                return new SearchByGenre(books);
            default:
                return null; // Return null if the choice is invalid
        }
    }
}
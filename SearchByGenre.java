import java.util.List;

public class SearchByGenre implements SearchStrategy {
    private List<Book> books;

    public SearchByGenre(List<Book> books) {
        this.books = books;
    }

    @Override
    public void searchMethod(String query) {
        boolean found = false;
        for (Book book : books) {
            if (book.getGenre().equalsIgnoreCase(query)) {
                System.out.println("Found: " + book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found with genre: " + query);
        }
    }
}
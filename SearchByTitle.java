import java.util.List;

public class SearchByTitle implements SearchStrategy {
    private List<Book> books;

    public SearchByTitle(List<Book> books) {
        this.books = books;
    }

    @Override
    public void searchMethod(String query) {
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(query)) {
                System.out.println("Found: " + book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No book found with the title: " + query);
        }
    }
}
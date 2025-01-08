import java.util.List;

public class SearchByAuthor implements SearchStrategy {
    private List<Book> books;

    public SearchByAuthor(List<Book> books) {
        this.books = books;
    }

    @Override
    public void searchMethod(String query) {
        boolean found = false;
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(query)) {
                System.out.println("Found: " + book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found by author: " + query);
        }
    }
}
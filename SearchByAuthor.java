import java.util.List;

public class SearchByAuthor extends BaseSearchStrategy {
    public SearchByAuthor(List<Book> books){
        super(books);
    }

    @Override
    public void searchMethod(String query) {
        System.out.println("Searching by author: " + query);
        for (Book book : books) {
            if (book.getAuthor().equals(query)) {
                System.out.println("Found author: " + book.getAuthor());
            }
        }
    }
}

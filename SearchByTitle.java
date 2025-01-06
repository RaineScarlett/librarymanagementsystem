import java.util.List;

public class SearchByTitle extends BaseSearchStrategy {

    public SearchByTitle(List<Book> books) {
        super(books);
    }

    @Override
    public void searchMethod(String query) {
        System.out.println("searching for books with title" + query);
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(query)) {
                System.out.println(book);
            }
        }
    }
}

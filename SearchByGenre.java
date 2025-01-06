import java.util.List;

public class SearchByGenre extends BaseSearchStrategy {
    public SearchByGenre(List<Book> books) {
        super(books);
    }

    @Override
    public void searchMethod(String query) {
        System.out.println("searching for books in genre: " + query);
        for (Book book : books) {
            if (book.getGenre().equals(query)) {
                System.out.println(book);
            }
        }
    }
}

public class Book {
    private String _title;
    private String _author;
    private String _genre;

    public Book(String title) {
        this._title = title;
    }

    // also needs a location (shelf number and floor number)

    public Book(String title, String author, String genre) {
        this._title = title;
        this._author = author;
        this._genre = genre;
    }

    // Getters
    public String getTitle() {
        return _title;
    }

    public String getAuthor() {
        return _author;
    }

    public String getGenre() {
        return _genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + _title + '\'' +
                ", author='" + _author + '\'' +
                ", genre='" + _genre + '\'' +
                '}';
    }
}

public class Book {
    private String title;
    private String author;
    private String genre;
    protected String bookType;
    private Shelf shelf;  // Reference to the shelf the book is on

    // Constructor to create a book
    public Book(BookBuilder<?> builder) {
        this.title = builder.title;
        this.author = builder.author;
        this.genre = builder.genre;
        this.bookType = builder.bookType;
    }

    public static abstract class BookBuilder<T extends BookBuilder<T>> {
        private String title;
        private String author;
        private String genre;
        protected String bookType;

        public T title(String title) {
            this.title = title;
            return self();
        }

        public T author(String author) {
            this.author = author;
            return self();
        }
        public T genre(String genre) {
            this.genre = genre;
            return self();
        }
        public T type(String type) {
            this.bookType = type;
            return self();
        }
        protected abstract T self();

        public abstract Book build();
    }

    public static class PhysicalBookBuilder extends BookBuilder<PhysicalBookBuilder> {
        public PhysicalBookBuilder() {
            this.bookType = "physical book";
        }

        @Override
        protected PhysicalBookBuilder self() {
            return this;
        }

        @Override
        public Book build() {
            return new Book(this);
        }
    }

    public static class EBookBuilder extends BookBuilder<EBookBuilder> {
        public EBookBuilder() {
            this.bookType = "ebook";
        }

        protected EBookBuilder self() {
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }

    // Getter for title
    public String getTitle() {
        return title;
    }

    // Getter for author
    public String getAuthor() {
        return author;
    }

    // Getter for genre
    public String getGenre() {
        return genre;
    }

    // Setter for shelf (to associate a book with a shelf)
    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }

    // Getter for shelf
    public Shelf getShelf() {
        return shelf;
    }


    // Overriding toString() to include shelf and floor details
    @Override
    public String toString() {
        String shelfName = (shelf != null) ? shelf.getName() : "No shelf assigned";
        String floorName = (shelf != null && shelf.getFloor() != null) ? shelf.getFloor().getName() : "No floor assigned";
        if (bookType.equals("ebook")) {
            return "E-Book: " + title + ", Author: " + author + ", Genre: " + genre + ", Type: " + bookType;
        }

        return "Book: " + title + ", Author: " + author + ", Genre: " + genre + ", Shelf: " + shelfName + ", Floor: " + floorName + ", Type: " + bookType;
    }
}

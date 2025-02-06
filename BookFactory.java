public class BookFactory {

    // Method to create a physical book
    public static Book createPhysicalBook(String title, String author, String genre, Shelf shelf) {
        return new Book.PhysicalBookBuilder()
                .title(title)
                .author(author)
                .genre(genre)
                .fileFormat("N/A") // Physical books don't have a file format
                .build();
    }

    // Method to create an ebook
    public static Book createEBook(String title, String author, String genre, String fileFormat) {
        return new Book.EBookBuilder()
                .title(title)
                .author(author)
                .genre(genre)
                .fileFormat(fileFormat)
                .build();
    }
}
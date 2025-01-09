import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create floors
        Floor firstFloor = new Floor("First Floor");
        Floor secondFloor = new Floor("Second Floor");

        User alice = new User("Alice");
        User bob = new User("Bob");
        User dennieboygames = new User("Dennieboygames");

        // Create shelves
        Shelf fictionShelf = new Shelf("Fiction Shelf");
        Shelf fantasyShelf = new Shelf("Fantasy Shelf");
        Shelf classicShelf = new Shelf("Classic Shelf");
        Shelf scienceShelf = new Shelf("Science Shelf");

        // Add shelves to floors
        firstFloor.addShelf(fictionShelf);
        firstFloor.addShelf(fantasyShelf);
        secondFloor.addShelf(classicShelf);
        secondFloor.addShelf(scienceShelf);

        // Create books and assign them to shelves
        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "Fiction");
        Book book2 = new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", "Fantasy");
        Book book3 = new Book("The Catcher in the Rye", "J.D. Salinger", "Fiction");
        Book book4 = new Book("A Game of Thrones", "George R.R. Martin", "Fantasy");
        Book book5 = new Book("1984", "George Orwell", "Classic");
        Book book6 = new Book("The Origin of Species", "Charles Darwin", "Science");

// Subscribe users to shelves
        fictionShelf.addObserver(alice);
        fantasyShelf.addObserver(bob);
        scienceShelf.addObserver(dennieboygames);

        fictionShelf.addBook(book1);
        fictionShelf.addBook(book3);
        fantasyShelf.addBook(book2);
        fantasyShelf.addBook(book4);
        classicShelf.addBook(book5);
        scienceShelf.addBook(book6);

        // Combine all books for searching
        List<Book> books = new ArrayList<>();
        books.addAll(firstFloor.getAllBooks());
        books.addAll(secondFloor.getAllBooks());

        // Initialize the library search
        LibrarySearch librarySearch = new LibrarySearch();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary Search");
            System.out.println("1. Search by Title");
            System.out.println("2. Search by Author");
            System.out.println("3. Search by Genre");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 4) {
                System.out.println("Exiting program.");
                break;
            }

            System.out.print("Enter your search query: ");
            String query = scanner.nextLine();

            // Create the base strategy based on user choice
            SearchStrategy strategy;
            switch (choice) {
                case 1:
                    strategy = new SearchByTitle(books);
                    break;
                case 2:
                    strategy = new SearchByAuthor(books);
                    break;
                case 3:
                    strategy = new SearchByGenre(books);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    continue;
            }

            // Apply the decorator for logging functionality
            SearchStrategy decoratedStrategy = new LoggingSearchDecorator(strategy);

            // Set the decorated strategy to the library search
            librarySearch.setSearchStrategy(decoratedStrategy);

            // Execute the decorated search
            librarySearch.executeSearch(query);
        }

        scanner.close();
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create floors
        Floor firstFloor = new Floor("First Floor");
        Floor secondFloor = new Floor("Second Floor");
        Floor thirdFloor = new Floor("Third Floor");

        // Create librarian
        List<Librarian> librarians = new ArrayList<>();
        librarians.add(new Librarian("Lisa", "thebestlibrarianever"));
        librarians.add(new Librarian("Bethany", "ilovebooks"));

        // Create users
        User alice = new User("Alice");
        User bob = new User("Bob");
        User dennieboygames = new User("Dennieboygames");
        User richard = new User("Richard");
        User pjotrJan = new User("PjotrJan");

        // Create shelves
        Shelf fictionShelf = new Shelf("Fiction Shelf");
        Shelf fantasyShelf = new Shelf("Fantasy Shelf");
        Shelf classicShelf = new Shelf("Classic Shelf");
        Shelf scienceShelf = new Shelf("Science Shelf");
        Shelf philosophyShelf = new Shelf("Philosophy Shelf");
        Shelf horrorShelf = new Shelf("Horror Shelf");
        Shelf gothicShelf = new Shelf("Gothic Shelf");
        Shelf biographyShelf = new Shelf("Biography Shelf");
        Shelf treatiseShelf = new Shelf("Treatise Shelf");

        // Add shelves to floors
        firstFloor.addShelf(fictionShelf);
        firstFloor.addShelf(fantasyShelf);
        secondFloor.addShelf(classicShelf);
        secondFloor.addShelf(scienceShelf);
        secondFloor.addShelf(treatiseShelf);
        thirdFloor.addShelf(philosophyShelf);
        thirdFloor.addShelf(horrorShelf);
        thirdFloor.addShelf(gothicShelf);
        thirdFloor.addShelf(biographyShelf);

        // Create books and assign them to shelves using the BookFactory
        // Physical Books
        Book book1 = BookFactory.createPhysicalBook("The Great Gatsby", "F. Scott Fitzgerald", "Fiction", null);
        Book book2 = BookFactory.createPhysicalBook("The Catcher in the Rye", "J.D. Salinger", "Fiction", null);
        Book book3 = BookFactory.createPhysicalBook("A Game of Thrones", "George R.R. Martin", "Fantasy", null);
        Book book4 = BookFactory.createPhysicalBook("1984", "George Orwell", "Classic", null);
        Book book5 = BookFactory.createPhysicalBook("The Origin of Species", "Charles Darwin", "Science", null);
        Book book6 = BookFactory.createPhysicalBook("The Second Sex", "Simone de Beauvoir", "Philosophy", null);

        // E-Books
        Book book7 = BookFactory.createEBook("No Exit", "Jean-Paul Sartre", "Philosophy", "PDF");
        Book book8 = BookFactory.createEBook("Dracula", "Bram Stoker", "Horror", "EPUB");
        Book book9 = BookFactory.createEBook("Frankenstein", "Mary Shelley", "Gothic", "AZW3");
        Book book10 = BookFactory.createEBook("The Diary of a Young Girl", "Anne Frank", "Biography", "MOBI");
        Book book11 = BookFactory.createEBook("The King in Yellow", "Robert Chambers", "Horror", "AZW3");
        Book book12 = BookFactory.createEBook("The Festival", "H. P. Lovecraft", "Horror", "EPUB");

        // Remaining Physical Books
        Book book13 = BookFactory.createPhysicalBook("Art of War", "Sun Tzu", "Treatise", null);
        Book book14 = BookFactory.createPhysicalBook("Either/Or", "Søren Kierkegaard", "Philosophy", null);
        Book book15 = BookFactory.createPhysicalBook("Fear and Trembling", "Søren Kierkegaard", "Philosophy", null);

        // Subscribe users to shelves
        fictionShelf.addObserver(alice);
        fantasyShelf.addObserver(bob);
        scienceShelf.addObserver(dennieboygames);
        philosophyShelf.addObserver(richard);
        horrorShelf.addObserver(pjotrJan);

        // Add books to shelves
        fictionShelf.addBook(book1);
        fictionShelf.addBook(book2);
        fantasyShelf.addBook(book3);
        classicShelf.addBook(book4);
        scienceShelf.addBook(book5);
        philosophyShelf.addBook(book6);
        philosophyShelf.addBook(book7);
        horrorShelf.addBook(book8);
        gothicShelf.addBook(book9);
        biographyShelf.addBook(book10);
        horrorShelf.addBook(book11);
        horrorShelf.addBook(book12);
        treatiseShelf.addBook(book13);
        philosophyShelf.addBook(book14);
        philosophyShelf.addBook(book15);

        // Combine all books for searching
        List<Book> books = new ArrayList<>();
        books.addAll(firstFloor.getAllBooks());
        books.addAll(secondFloor.getAllBooks());
        books.addAll(thirdFloor.getAllBooks());

        // Initialize the library search and the factory
        LibrarySearch librarySearch = new LibrarySearch();
        SearchStrategyFactory factory = new SearchStrategyFactory(books);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary Search");
            System.out.println("1. Search by Title");
            System.out.println("2. Search by Author");
            System.out.println("3. Search by Genre");
            System.out.println("4. Librarian Login");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 5) {
                System.out.println("Exiting program.");
                break;
            } else if (choice == 4) {
                System.out.println("\nLibrarian login:");
                System.out.print("What is your password? ");
                String password = scanner.nextLine();

                boolean loginSuccessful = false;

                // Check all librarians for a successful login
                for (Librarian librarian : librarians) {
                    if (librarian.librarianLogin(password)) {
                        loginSuccessful = true;
                        while (true) {
                            System.out.println("\nLibrarian Options:");
                            System.out.println("1. View All Books");
                            System.out.println("2. Log Out");
                            System.out.print("Choose an option: ");

                            int librarianChoice = scanner.nextInt();
                            scanner.nextLine();

                            if (librarianChoice == 1) {
                                // See all of the library's books and shelves at once
                                for (Book book : books) {
                                    if (book.getBookType().equals("ebook")) {
                                        System.out.println(book.getBookType() + ": " + book.getTitle() + " by " + book.getAuthor() + ", Format: " + book.getFileFormat());
                                    } else {
                                        System.out.println(book.getBookType() + ": " + book.getTitle() + " by " + book.getAuthor() + " at " + book.getShelf());
                                    }
                                }
                            } else if (librarianChoice == 2) {
                                System.out.println("\nGoodnight, " + librarian.getLibrarianName() + ". Logging out...");
                                break;
                            } else {
                                System.out.println("Invalid option, please select again.");
                            }
                        }
                        break; // Break the for-loop if login is successful
                    }
                }

                if (!loginSuccessful) {
                    System.out.println("Login unsuccessful. Please try again.");
                }
                continue;
            }

            System.out.print("Enter your search query: ");
            String query = scanner.nextLine();

            // Use the factory to create the correct search strategy
            SearchStrategy strategy = factory.createSearchStrategy(choice);

            // If the strategy is null, the user chose an invalid option
            if (strategy == null) {
                System.out.println("Invalid search option.");
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
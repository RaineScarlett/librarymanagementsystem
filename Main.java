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

        // Create books and assign them to shelves
        // Physical Books
        Book book1 = new Book.PhysicalBookBuilder().title("The Great Gatsby").author("F. Scott Fitzgerald").genre("Fiction").build();
        Book book2 = new Book.PhysicalBookBuilder().title("The Catcher in the Rye").author("J.D. Salinger").genre("Fiction").build();
        Book book3 = new Book.PhysicalBookBuilder().title("A Game of Thrones").author("George R.R. Martin").genre("Fantasy").build();
        Book book4 = new Book.PhysicalBookBuilder().title("1984").author("George Orwell").genre("Classic").build();
        Book book5 = new Book.PhysicalBookBuilder().title("The Origin of Species").author("Charles Darwin").genre("Science").build();
        Book book6 = new Book.PhysicalBookBuilder().title("The Second Sex").author("Simone du Beauvoir").genre("Philosophy").build();

// E-Books
        Book book7 = new Book.EBookBuilder().title("No Exit").author("Jean Paul Sartre").genre("Philosophy").fileFormat("PDF").build();
        Book book8 = new Book.EBookBuilder().title("Dracula").author("Bram Stoker").genre("Horror").fileFormat("EPUB").build();
        Book book9 = new Book.EBookBuilder().title("Frankenstein").author("Mary Shelley").genre("Gothic").fileFormat("AZW3").build();
        Book book10 = new Book.EBookBuilder().title("The Diary of a Young Girl").author("Anne Frank").genre("Biography").fileFormat("MOBI").build();
        Book book11 = new Book.EBookBuilder().title("The King in Yellow").author("Robert Chambers").genre("Horror").fileFormat("AZW3").build();
        Book book12 = new Book.EBookBuilder().title("The Festival").author("H. P. Lovecraft").genre("Horror").fileFormat("EPUB").build();

// Remaining Physical Books
        Book book13 = new Book.PhysicalBookBuilder().title("Art of War").author("Sun Tzu").genre("Treatise").build();
        Book book14 = new Book.PhysicalBookBuilder().title("Either").author("Soren Kierkegaard").genre("Philosophy").build();
        Book book15 = new Book.PhysicalBookBuilder().title("Or").author("Soren Kierkegaard").genre("Philosophy").build();


        // Subscribe users to shelves
        fictionShelf.addObserver(alice);
        fantasyShelf.addObserver(bob);
        scienceShelf.addObserver(dennieboygames);
        philosophyShelf.addObserver(richard);
        horrorShelf.addObserver(pjotrJan);

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
                                    if(book.getBookType() == "ebook"){
                                        System.out.println(book.getBookType() + ": " + book.getTitle() + " by " + book.getAuthor() + "format: " + book.getFileFormat());
                                    } else
                                    System.out.println(book.getBookType() + ": " + book.getTitle() + " by " + book.getAuthor() + " at " + book.getShelf());
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
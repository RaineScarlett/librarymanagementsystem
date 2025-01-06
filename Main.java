import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", "Fiction"));
        books.add(new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", "Fantasy"));
        books.add(new Book("The Catcher in the Rye", "J.D. Salinger", "Fiction"));
        books.add(new Book("A Game of Thrones", "George R.R. Martin", "Fantasy"));

        LibrarySearch librarySearch = new LibrarySearch();
        Scanner scanner = new Scanner(System.in);

        while(true) {
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
                break; // Exit the loop if the user chooses 4
            }

            System.out.print("Enter your search query: ");
            String query = scanner.nextLine(); // Get user input for search query

            switch (choice) {
                case 1:
                    librarySearch.setSearchStrategy(new SearchByTitle(books));
                    librarySearch.executeSearch(query);
                    break;
                case 2:
                    librarySearch.setSearchStrategy(new SearchByAuthor(books));
                    librarySearch.executeSearch(query);
                    break;
                case 3:
                    librarySearch.setSearchStrategy(new SearchByGenre(books));
                    librarySearch.executeSearch(query);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}

# Library Management System

## Project hosted on:
https://github.com/RaineScarlett/randomjavaapplicationforsd

## Overview
For our second project we are making a simple library management system.
for this we aim to implement different design pattens. the system will among others feature searching for books by title, author or genre.
there are also different shelves and floors that the books are located on. which will also be displayed.
---

## Features
1. **Search for Books**:
   - Search by Title, Author, or Genre.
   - Logs search results to track queries.
   - See all available books at once (if you're a librarian).

2. **Organization**:
   - Books are organized into shelves, and shelves are part of specific floors.
   - Users can easily locate books by their floor and shelf.

3. **Notifications**:
   - Users can subscribe to shelves to receive real-time notifications when new books are added.

4. **Login**
   - When starting the application, you can login as a librarian.
   - When not logged in, you can get all basic functionalities.
   - When logged in as a librarian, you have the option of viewing all available books.
   - Librarians also get personalised login and logout messages with their names.
---

## Design Patterns Used
1. **Strategy Pattern**:
   we used the strategy pattern to have three different ways to search for books.
   in our application you can search for books by title, genre or author.
   the strategy patterns is primarily useful for making the app expandable.
   it is very ease to add a new book, all that is needed for that is a new search stratagy with the same structure as the others.
   afterwards you can call it in the main file like this:
   `Book bookNumber = new Book("title", "author", "genre");`
   then you can also add the book to a shelf and a floor like this:
   `shelfName.addBook(book);`.
   finally, you can add a shelf to a floor:`Floorname.addShelf(shelfName);`
   We have created three search strategies:
   - SearchByTitle: Search for books based on their title.
   - SearchByGenre: Search for books by genre.
   - SearchByAuthor: Search for books by author.
     Each of these strategies implements the SearchStrategy interface, which consists of the searchMethod(String query) method.
     The LibrarySearch.java holds the current search strategy and allows swapping between them, it has the following structure:
   ```
   public class LibrarySearch {
    private SearchStrategy strategy;

    public void setSearchStrategy(SearchStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeSearch(String query) {
        if(strategy == null) {
            System.out.println("Search strategy is not set");
            return;
        }
        strategy.searchMethod(query);
    }
   }
   ```
   the setSearchStrategy() is used in Main.java in the following piece of code, which sets the search strategy based on user input (and has a bit of error handling):
   ```
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
   ```

2. **Observer Pattern**:
   The observer pattern allows for a subscription, and then a notification whenever something changes. we implement this by using users,
   and then when a new book gets added a notification will be given to the user. that looks like this:  `User Name = new User("Name");`
   you can then add the user to a shelf: `ShelfName.addObserver(Name); `
   this is especially useful for the users convenience, instead of having to check for a new book everytime.
   the user can subscribe to a shelf with books they are interested in and get notified whenever a new book is added.
   This takes functionality from the Observer.java:
   ```
   public interface Observer {
    void update(String message);
   }
   ```
   and Observable.java:
   ```
   public abstract class Observable {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
   }
   ```
   Here is an example of a notification received on the terminal:
   `Richard received notification: New book added: The Second Sex to Philosophy Shelf`

3. **Decorator Pattern**:
   The decorator pattern is used for logging information upon a search. when a user searches for a book,
   the search query gets logged, along with the time elapsed. this is useful for the users to see if their query was actually correct,
   and they got the expected output. that is the main function of the decorator pattern, to extend on the main application,
   but aren't an actual part of it.
   In the LoggingSearchDecorator.java class, we have the following method:
   ```
   public void searchMethod(String query) {
        // Track the start time
        long startTime = System.nanoTime();

        // Perform the search
        System.out.println("[LOG] executing search for query: " + query);
        strategy.searchMethod(query); // Delegate to the actual search strategy

        // Track the end time
        long endTime = System.nanoTime();

        // Calculate the time elapsed in milliseconds
        long timeElapsed = (endTime - startTime) / 1_000_000;

        // Log the time elapsed
        System.out.println("[LOG] Time taken for search: " + timeElapsed + " ms");
    }
    ```
   which gets the time at two different points, first when the search hasn't been performed, and the second after the search is performed. The `System.out.println("[LOG] executing search for query: " + query);` prints the logging details and the searchMethod() is set to the query parameter, which is itself received when calling the function in Main.java with the line `SearchStrategy decoratedStrategy = new LoggingSearchDecorator(strategy);`

4. **Factory Pattern**:
   The factory pattern is mostly used for abstraction and simplification of classes. That is exactly what we are doing here, though ours is a simple one.
   The search strategies are abstracted into a factory using the SearchStrategyFactory.java class as follows.
   ```
   public class SearchStrategyFactory {
    private List<Book> books;

    public SearchStrategyFactory(List<Book> books) {
        this.books = books;
    }

    public SearchStrategy createSearchStrategy(int choice) {
        switch (choice) {
            case 1:
                return new SearchByTitle(books);
            case 2:
                return new SearchByAuthor(books);
            case 3:
                return new SearchByGenre(books);
            default:
                return null; // Return null if the choice is invalid
         }
    }
   }
   
   ```
   So in the main file all that needs to be added is this:
   `SearchStrategy strategy = factory.createSearchStrategy(choice);`, where the choice parameter that is given to select a search strategy is given by user input. You could abstract even more in theory.
   say you want to implement normal books and ebooks. Instead of making another class for that, you can make a factory and just call the factory in the main file. This doesn't necessarily decrease the amount of code,
   but it abstracts and simplifies the code, as well as preventing it from becoming messy, making it easier for the developers to work with, and to maintain. So that is what we did:
   ```
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
   ```
   this file is used to abstract the builder into a factory. so a book can now be either a book or an ebook. we then call the factory like this when adding books:
   ```
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
   ```
   instead of calling the builder directly, you call the factory instead. This again helps with code simplification, abstraction and readability.

5. **Command Pattern**
   We used the command pattern to limit certain functionalities of the application to different types of users. So, while regular users can make searches, librarians can log in, get a personalised login and log out message, and more importantly can view a list of all books at once which other users can't.
   First the librarian logs in with this function of Librarian.java:
   ```
   public boolean librarianLogin(String password){
        if (password.equals(_librarianPassword)) {
            System.out.println("\nGood evening, " + _librarianName);
            return true;
        }
        else {
            System.out.println("Login unsuccessful.");
            return false;
        }
    }
   ```
   And then in Main.java, if the login is successful, they get a menu from here:
   ```
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
    }}} don't mind these curly brakets, they're just here so the readme doesn't complain, please check Main.java for the actual full thing              
   ```
   having the option to view all books, unlike regular users. Here is a closer look at their options' functionalities:
   ```
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
   ```
   As you can see, they can view all books of the library at once, or get custom messages for logging in and out depending who they are. For example, Bethany would receive `Good evening, Bethany` when logging out and Lisa would have her name too.
   Each option selected by the librarian is treated as a command that can be executed independently.

6. **Builder Pattern**
   We used the builder pattern in our application to create different types of books, now being split between physical ones and ebooks.
   By using the builder pattern, we ensure that the creation of book objects is flexible and easy to read. For example, a physical book can be created using the following code in Main.java:  
   `Book book1 = new Book.PhysicalBookBuilder().title("The Great Gatsby").author("F. Scott Fitzgerald").genre("Fiction").build();`
   and an ebook with:
   `Book book7 = new Book.EBookBuilder().title("No Exit").author("Jean Paul Sartre").genre("Philosophy").fileFormat("PDF").build();`
   As you can see, these use different builder functions, which both extend an abstract BookBuilder class inside Book.java:
   ```
   public static abstract class BookBuilder<T extends BookBuilder<T>> {
        private String title;
        private String author;
        private String genre;
        protected String bookType;
        private String fileFormat;

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

        public T fileFormat(String fileFormat) {
            this.fileFormat = fileFormat;
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
    ```
   This format ensures that we can separate the two types of books and their format while still sharing most other characteristics.

7. **Proxy Pattern**
   Our application uses the proxy pattern to provide different information to the user depending on what type of book they search for. When the book is physical, you can see its shelf and floor, but when it's an ebook that information is, of course, not provided. Instead, ebooks have a file format associated to them.
   This is processed by the following code piece in Main.java:
   ```
   for (Book book : books) {
         if(book.getBookType() == "ebook"){
            System.out.println(book.getBookType() + ": " + book.getTitle() + " by " + book.getAuthor() + "format: " + book.getFileFormat());
         } else
            System.out.println(book.getBookType() + ": " + book.getTitle() + " by " + book.getAuthor() + " at " + book.getShelf());
         }
    ```
   The way it works is as follows, for each of the books called that will be displayed on the terminal, you check if they are physical or digital with `book.getBookType()`. Then the if statement separates both types of book and the way they will be displayed, giving ebooks `"format: " + book.getFileFormat()` and physical books `" at " + book.getShelf()`. Each type of book also has its own builder (as follows inside Book.java):
   ```
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
    ```
   which is where the type is set in the first place before Main.java can use it for the proxy pattern.
---

1. **Interact with the System**:
   - Choose from the menu options to search for books or explore the notifications feature.
   - Example:
     ```
     Library Search
     1. Search by Title
     2. Search by Author
     3. Search by Genre
     4. Librarian Login
     5. Exit
     Choose an option: 1
     Enter your search query: The Great Gatsby
     ```
2. **View Notifications**:
   - Notifications for subscribed users will be displayed directly in the console when new books are added.

---

## Sample Output
### Notifications:
```
Notification for Alice: A new book "The Great Gatsby" was added to Fiction Shelf.
Notification for Bob: A new book "Harry Potter and the Philosopher's Stone" was added to Fantasy Shelf.
```

### Search Results:
```
Search Results for "No Exit":
Found: E-Book: No Exit, Author: Jean Paul Sartre, Genre: Philosophy, Type: ebook, Format: PDF
```

### Librarian Login:
```
Librarian Options:
1. View All Books
2. Log Out
```

### View all books (locked to librarians):
```
ebook: The Diary of a Young Girl by Anne Frank
```

This application was developed by: 
- Bas (bas-naald)
- Reyna (RaineScarlett) 
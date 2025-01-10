# Library Management System

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
finally, you can add a shelf to a floor:`FLoorname.addShelf(shelfName);`

2. **Observer Pattern**:
the observer pattern allows for a subscription, and then a notification whenever something changes. we implement this by using users,
and then when a new book gets added a notification will be given to the user. that looks like this:  `User Name = new User("Name");` 
you can then add the user to a shelf: `ShelfName.addObserver(Name); `
this is especially useful for the users convenience, instead of having to check for a new book everytime. 
the user can subscribe to a shelf with books they are interested in and get notified whenever a new book is added.

3. **Decorator Pattern**:
the decorator pattern is used for logging information upon a search. when a user searches for a book, 
the search query gets logged, along with the time elapsed. this is useful for the users to see if their query was actually correct,
and they got the expected output. that is the main function of the decorator pattern, to extend on the main application,
but aren't an actual part of it.

4. **Factory Method**:
The factory pattern is mostly used for abstraction and simplification of classes. that is exactly what we are doing here.
the search strategies are abstracted into a factory. so in the main file all that needs to be added is this:
`SearchStrategy strategy = factory.createSearchStrategy(choice);`. you could abstract even more in theory.
say you want to implement normal books and ebooks. instead of making another class for that, 
you can make a factory and just call the factory in the main file. this doesn't necessarily decrease the amount of code,
but it abstracts and simplifies the code, as well as preventing it from becoming messy. 
making it easier for the developers to work with, and to maintain.

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
Search Results for "Fiction":
1. Title: The Great Gatsby, Author: F. Scott Fitzgerald, Genre: Fiction, Shelf: Fiction Shelf, Floor: First Floor
2. Title: The Catcher in the Rye, Author: J.D. Salinger, Genre: Fiction, Shelf: Fiction Shelf, Floor: First Floor
```

# Library Management System with Structural Design Patterns

## Project Description

The **Library Management System with Structural Design Patterns** project is a Java-based library management system that demonstrates the use of structural design patterns to create a flexible, scalable, and maintainable system. The primary design patterns implemented in this project include **Decorator**, **Adapter**, **Bridge**, and **Strategy**. Each pattern is used to solve specific design challenges, such as dynamically adding behaviors, adapting data from external sources, decoupling abstractions, and providing interchangeable search strategies. This project serves as both a functional library management application and a study in the effective use of structural design patterns.

## Author

- **Arda Baran**

## Features

- **Flexible Book Data Management**: The system can handle book information from various data formats (e.g., JSON) using the Adapter pattern.
- **Enhanced Book Functionality**: The system allows the dynamic addition of features to books, such as reviews and discounts, using the Decorator pattern.
- **Multi-format Book Representation**: Books can be represented in multiple formats, including eBooks and printed books, via the Bridge pattern.
- **Customizable Search Strategies**: Users can search for books by different criteria (e.g., ISBN, author, publisher) using interchangeable search strategies implemented with the Strategy pattern.
- **Singleton Management for Authors and Publishers**: The system uses Singleton pattern-based manager classes to handle the unique instances of authors and publishers, ensuring efficient memory usage and centralized control.

## Technologies and Data Structures Used

- **Programming Language**: Java
- **IDE**: Eclipse
- **Data Structures and Design Patterns**:
  - **AVL Tree**: Used in stragety pattern for efficient lookups for ISBN.
  - **HashMap**: Used in Singleton managers for efficient lookups of authors and publishers.
  - **HashSet**: To maintain a collection of unique books for each author and publisher.
  - **Object Oriented Programming**: Inheritence,Abstract classes,Interfaces,Encapsulation 
 - **Design Patterns**:
    - **Decorator Pattern**: Adds dynamic functionality to books, such as discounts and user reviews.
    - **Adapter Pattern**: Adapts external JSON data to the system’s internal `Book` class structure.
    - **Bridge Pattern**: Separates book content from its format, allowing flexible representations.
    - **Strategy Pattern**: Provides multiple search algorithms for finding books by various criteria.
    - **Singleton Pattern**: Manages unique instances of authors and publishers for memory efficiency and centralized management.
 
## Classes

### 1. **Book**
   - Represents a book in the library system, containing attributes such as `bookName`, `author`, `publisher`, `isbn`, `price`, and more.
   - Supports dynamic decoration to add features like user reviews and special discounts.

### 2. **Author**
   - Represents an author who has written one or more books.
   - Stores the author’s name and a `HashSet<Book>` for managing unique books authored by them.

### 3. **Publisher**
   - Represents a publisher responsible for publishing one or more books.
   - Contains the publisher’s name and a `HashSet<Book>` for managing unique books published by them.

### 4. **SingletonAuthorManager**
   - Manages the unique instances of `Author` objects using the Singleton pattern.
   - Provides methods to add books to authors and retrieve books by author name.

### 5. **SingletonPublisherManager**
   - Manages unique instances of `Publisher` objects using the Singleton pattern.
   - Provides methods to add books to publishers and retrieve books by publisher name.

### 6. **Decorator Pattern Classes**
   - **BookComponent**: An interface representing the base component for books.
   - **ConcreteBook**: The concrete implementation of `BookComponent`.
   - **BookDecorator**: Abstract decorator class implementing `BookComponent` and containing a reference to a `BookComponent`.
   - **SpecialDiscountDecorator**: A decorator that adds a special discount to a book’s price.
   - **UserReviewDecorator**: A decorator that adds user reviews to a book.

### 7. **Adapter Pattern Classes**
   - **ExternalBookData**: Interface for external book data, defining methods to access external book properties.
   - **JsonBookData**: Implementation of `ExternalBookData` for handling JSON-formatted book data.
   - **BookAdapter**: Adapts `ExternalBookData` to create a `Book` object compatible with the library system.

### 8. **Bridge Pattern Classes**
   - **BookFormat**: Interface representing various formats of books (e.g., eBooks, printed books).
   - **FormattedBook**: The main abstraction, which contains a reference to a `BookFormat` and a `Book`.
   - **PrintedBookFormat**: Implementation of `BookFormat` for printed books.
   - **EBookFormat**: Implementation of `BookFormat` for eBooks.

### 9. **Strategy Pattern Classes**
   - **SearchStrategy**: Interface defining the search method for finding books.
   - **BookSearch**: Context class that uses a `SearchStrategy` to perform searches.
   - **IsbnSearchStrategy**: Concrete strategy for searching books by ISBN.
   - **AuthorSearchStrategy**: Concrete strategy for searching books by author name.
   - **PublisherSearchStrategy**: Concrete strategy for searching books by publisher name.

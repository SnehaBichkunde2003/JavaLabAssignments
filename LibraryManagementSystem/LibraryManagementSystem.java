import java.io.*;
import java.util.*;

class Book {
    private String bookName;
    private String author;
    private String isbn;
    private boolean availability;

    public Book(String bookName, String author, String isbn, boolean availability) {
        this.bookName = bookName;
        this.author = author;
        this.isbn = isbn;
        this.availability = availability;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return isbn;
    }

    public boolean isAvailable() {
        return availability;
    }

    public void setAvailable(boolean availability) {
        this.availability = availability;
    }

    public String toString() {
        return bookName + "," + author + "," + isbn + "," + availability;
    }

    public static Book fromString(String bookString) {
        String[] parts = bookString.split(",");
        return new Book(parts[0], parts[1], parts[2], Boolean.parseBoolean(parts[3]));
    }
}

class User {
    private String name;
    private String id;
    private String contactInfo;

    public User(String name, String id, String contactInfo) {
        this.name = name;
        this.id = id;
        this.contactInfo = contactInfo;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return id;
    }

    public String getContactInfo() {
        return contactInfo;
    }
}

class Library {
    private List<Book> books;
    private List<User> users;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
        loadDataFromFile();
    }

    public void addBook(String bookName, String author, String isbn) {
        books.add(new Book(bookName, author, isbn, true));
        System.out.println("Book added successfully.");
        saveDataToFile();
    }

    public void searchBook(String searchTerm) {
        System.out.println("Search results:");
        boolean found = false;
        for (Book book : books) {
            if (book.getBookName().contains(searchTerm) || book.getAuthor().contains(searchTerm)) {
                System.out.println("Title: " + book.getBookName() + ", Author: " + book.getAuthor() + ", ISBN: " + book.getISBN() +
                        ", Availability: " + (book.isAvailable() ? "Available" : "Not Available"));
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching books found.");
        }
    }

    public void borrowBook(String isbn) {
        Book book = findBookByISBN(isbn);
        if (book != null) {
            if (book.isAvailable()) {
                book.setAvailable(false);
                System.out.println("Book successfully borrowed.");
                saveDataToFile();
            } else {
                System.err.println("Error: Book is not available for borrowing.");
            }
        } else {
            System.err.println("Error: Book not found.");
        }
    }

    public void returnBook(String isbn) {
        Book book = findBookByISBN(isbn);
        if (book != null) {
            if (!book.isAvailable()) {
                book.setAvailable(true);
                System.out.println("Book successfully returned.");
                saveDataToFile();
            } else {
                System.err.println("Error: The book is already available in the library.");
            }
        } else {
            System.err.println("Error: Book not found.");
        }
    }

    private Book findBookByISBN(String isbn) {
        for (Book book : books) {
            if (book.getISBN().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    private void saveDataToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("books.txt"))) {
            for (Book book : books) {
                writer.println(book.toString());
            }
        } catch (IOException e) {
            System.err.println("Error: Unable to save book data to file.");
        }
    }

    private void loadDataFromFile() {
        try (Scanner scanner = new Scanner(new File("books.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                books.add(Book.fromString(line));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Warning: Unable to load book data from file. Starting with an empty book collection.");
        }
    }

    public void generateAvailableBooksReport() {
        System.out.println("Available books in the library:");
        boolean found = false;
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println("Title: " + book.getBookName() + ", Author: " + book.getAuthor() + ", ISBN: " + book.getISBN());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No available books in the library.");
        }
    }
}

class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t\t Well Come to Library Management System");

        int choice;
        do {
            System.out.println("What do you want me to do?");
            System.out.println("Press 1 to add book");
            System.out.println("Press 2 to search for a book");
            System.out.println("Press 3 to borrow book");
            System.out.println("Press 4 to return a book");
            System.out.println("Press 5 to add new user");
            System.out.println("Press 6 to generate a report");
            System.out.println("Press 7 to exit");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                System.out.print("Enter the title: ");
                String title = scanner.next();
                scanner.nextLine(); // Consume newline
                System.out.print("Enter the author: ");
                String author = scanner.next();
                scanner.nextLine(); // Consume newline
                System.out.print("Enter the ISBN: ");
                String isbn = scanner.next();
                library.addBook(title, author, isbn);
                break;
                case 2:
                    System.out.print("Enter the search term (title or author): ");
                    String searchTerm = scanner.next();
                    library.searchBook(searchTerm);
                    break;
                case 3:
                    System.out.print("Enter the ISBN of the book to borrow: ");
                    String borrowIsbn = scanner.next();
                    library.borrowBook(borrowIsbn);
                    break;
                case 4:
                    System.out.print("Enter the ISBN of the book to return: ");
                    String returnIsbn = scanner.next();
                    library.returnBook(returnIsbn);
                    break;
                case 6:
                    library.generateAvailableBooksReport();
                    break;
                case 7:
                    System.out.println("Exiting the program. Saving data...");
                    break;
                default:
                    System.err.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 7);
    }
}

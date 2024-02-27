import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private String genre;
    private boolean available;

    public Book(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isAvailable() {
        return available;
    }

    public void borrow() {
        if (available) {
            available = false;
        } else {
            System.out.println("Book is not available for borrowing.");
        }
    }

    public void returnBook() {
        available = true;
    }
}

class Patron {
    private String name;
    private String contactInfo;
    private List<Book> borrowedBooks;

    public Patron(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        if (book.isAvailable()) {
            borrowedBooks.add(book);
            book.borrow();
            System.out.println(name + " borrowed " + book.getTitle());
        } else {
            System.out.println(book.getTitle() + " is not available for borrowing.");
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            book.returnBook();
            System.out.println(name + " returned " + book.getTitle());
        } else {
            System.out.println(name + " did not borrow " + book.getTitle());
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Sample books
        Book book1 = new Book("The Catcher in the Rye", "J.D. Salinger", "Fiction");
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", "Classics");

        // Sample patrons
        Patron patron1 = new Patron("Alice", "alice@email.com");
        Patron patron2 = new Patron("Bob", "bob@email.com");

        // Menu
        while (true) {
            System.out.println("1. Display Books");
            System.out.println("2. Display Patrons");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayBooks(book1, book2);
                    break;
                case 2:
                    displayPatrons(patron1, patron2);
                    break;
                case 3:
                    borrowBook(scanner, patron1, patron2, book1, book2);
                    break;
                case 4:
                    returnBook(scanner, patron1, patron2, book1, book2);
                    break;
                case 5:
                    System.out.println("Exiting the program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void displayBooks(Book... books) {
        System.out.println("Books:");
        for (Book book : books) {
            System.out.println(book.getTitle() + " by " + book.getAuthor() +
                    " (Genre: " + book.getGenre() + ", Available: " + book.isAvailable() + ")");
        }
    }

    private static void displayPatrons(Patron... patrons) {
        System.out.println("Patrons:");
        for (Patron patron : patrons) {
            System.out.println(patron.getName() + " (" + patron.getContactInfo() + ")");
            List<Book> borrowedBooks = patron.getBorrowedBooks();
            if (!borrowedBooks.isEmpty()) {
                System.out.println("Borrowed Books:");
                for (Book book : borrowedBooks) {
                    System.out.println("- " + book.getTitle());
                }
            }
        }
    }

    private static void borrowBook(Scanner scanner, Patron... patrons) {
        displayBooks();
        System.out.print("Enter book title to borrow: ");
        String bookTitle = scanner.next();
        Book selectedBook = findBookByTitle(bookTitle, patrons);
        if (selectedBook != null) {
            displayPatrons(patrons);
            System.out.print("Enter patron name to borrow the book: ");
            String patronName = scanner.next();
            Patron selectedPatron = findPatronByName(patronName, patrons);
            if (selectedPatron != null) {
                selectedPatron.borrowBook(selectedBook);
            } else {
                System.out.println("Patron not found.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void returnBook(Scanner scanner, Patron... patrons) {
        displayBooks();
        System.out.print("Enter book title to return: ");
        String bookTitle = scanner.next();
        Book selectedBook = findBookByTitle(bookTitle, patrons);
        if (selectedBook != null) {
            displayPatrons(patrons);
            System.out.print("Enter patron name to return the book: ");
            String patronName = scanner.next();
            Patron selectedPatron = findPatronByName(patronName, patrons);
            if (selectedPatron != null) {
                selectedPatron.returnBook(selectedBook);
            } else {
                System.out.println("Patron not found.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    private static Book findBookByTitle(String title, Patron... patrons) {
        for (Patron patron : patrons) {
            for (Book book : patron.getBorrowedBooks()) {
                if (book.getTitle().equalsIgnoreCase(title)) {
                    return book;
                }
            }
        }
        return null;
    }

    private static Patron findPatronByName(String name, Patron... patrons) {
        for (Patron patron : patrons) {
            if (patron.getName().equalsIgnoreCase(name)) {
                return patron;
            }
        }
        return null;
    }
}
import java.util.*;

class Book {
    int id;
    String title;
    String author;
    boolean isIssued;

    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    @Override
    public String toString() {
        return id + " | " + title + " | " + author + " | " + (isIssued ? "Issued" : "Available");
    }
}

class User {
    int id;
    String name;

    User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return id + " | " + name;
    }
}

class LibrarySystem {
    private List<Book> books = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private Map<Integer, Integer> issuedBooks = new HashMap<>(); // bookId -> userId

    private Scanner sc = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n=== LIBRARY MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Add User");
            System.out.println("4. View All Users");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addBook();
                case 2 -> viewBooks();
                case 3 -> addUser();
                case 4 -> viewUsers();
                case 5 -> issueBook();
                case 6 -> returnBook();
                case 7 -> {
                    System.out.println("Exiting... Thank you!");
                    return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private void addBook() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Enter Book Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Author Name: ");
        String author = sc.nextLine();
        books.add(new Book(id, title, author));
        System.out.println("✅ Book added successfully!");
    }

    private void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available!");
            return;
        }
        System.out.println("\n--- Book List ---");
        for (Book b : books) {
            System.out.println(b);
        }
    }

    private void addUser() {
        System.out.print("Enter User ID: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Enter User Name: ");
        String name = sc.nextLine();
        users.add(new User(id, name));
        System.out.println("✅ User added successfully!");
    }

    private void viewUsers() {
        if (users.isEmpty()) {
            System.out.println("No users found!");
            return;
        }
        System.out.println("\n--- User List ---");
        for (User u : users) {
            System.out.println(u);
        }
    }

    private void issueBook() {
        System.out.print("Enter Book ID to issue: ");
        int bookId = sc.nextInt();
        System.out.print("Enter User ID: ");
        int userId = sc.nextInt();

        Book book = findBook(bookId);
        User user = findUser(userId);

        if (book == null) {
            System.out.println("❌ Book not found!");
            return;
        }
        if (user == null) {
            System.out.println("❌ User not found!");
            return;
        }
        if (book.isIssued) {
            System.out.println("❌ Book is already issued!");
            return;
        }

        book.isIssued = true;
        issuedBooks.put(bookId, userId);
        System.out.println("✅ Book issued to " + user.name);
    }

    private void returnBook() {
        System.out.print("Enter Book ID to return: ");
        int bookId = sc.nextInt();

        Book book = findBook(bookId);
        if (book == null || !book.isIssued) {
            System.out.println("❌ Invalid book or not issued!");
            return;
        }

        book.isIssued = false;
        issuedBooks.remove(bookId);
        System.out.println("✅ Book returned successfully!");
    }

    private Book findBook(int id) {
        for (Book b : books) {
            if (b.id == id) return b;
        }
        return null;
    }

    private User findUser(int id) {
        for (User u : users) {
            if (u.id == id) return u;
        }
        return null;
    }

    public static void main(String[] args) {
        new LibrarySystem().start();
    }
}

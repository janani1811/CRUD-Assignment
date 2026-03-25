package CRUD;
import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class Main {

    static ArrayList<Book> books = new ArrayList<>();
    static ArrayList<Member> members = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n1.Create Member");
            System.out.println("2.Add Book");
            System.out.println("3.Issue Book");
            System.out.println("4.Return Book");
            System.out.println("5.Show Books");
            System.out.println("6.Remove Book");
            System.out.println("7.Update Book");
            System.out.println("8.Remove Member");
            System.out.println("9.Exit");

            int ch = sc.nextInt();

            switch (ch) {
                case 1: 
                	createMember(); 
                	break;
                case 2: 
                	addBook();
                	break;
                case 3:
                	issueBook();
                	break;
                case 4:
                	returnBook();
                	break;
                case 5: 
                	showBooks(); 
                	break;
                case 6:
                	removeBook(); 
                	break;
                case 7: 
                	updateBook(); 
                	break;
                case 8:
                	removeMember(); 
                	break;
                case 9: 
                	return;
            }
        }
    }

    static void createMember() {
        System.out.print("Enter id and name: ");
        int id = sc.nextInt();
        String name = sc.next();

        for (Member m : members) {
            if (m.memberId == id) {
                System.out.println("Member ID already exists");
                return;
            }
        }

        members.add(new Member(id, name, null, null, null));
        System.out.println("Member Created");
    }

    static void addBook() {
        System.out.print("Enter id, title, author: ");
        int id = sc.nextInt();
        String title = sc.next();
        String author = sc.next();

        books.add(new Book(id, title, author));
        System.out.println("Book Added (Available)");
    }

    static void issueBook() {
        System.out.print("Enter member id and book id: ");
        int mid = sc.nextInt();
        int bid = sc.nextInt();

        Member m = findMember(mid);
        Book b = findBook(bid);

        if (m == null || b == null) {
            System.out.println("Invalid ID");
            return;
        }

        if (b.isIssued) {
            System.out.println("Book already issued");
            return;
        }

        if (m.issuedBook != null) {
            System.out.println("Member already has a book");
            return;
        }

        m.issuedBook = b;
        b.isIssued = true;

        m.issueDate = LocalDate.now();
        m.dueDate = m.issueDate.plusDays(7);

        System.out.println("Book Issued");
    }

    static void returnBook() {
        System.out.print("Enter member id: ");
        int mid = sc.nextInt();

        Member m = findMember(mid);

        if (m == null) {
            System.out.println("Invalid Member");
            return;
        }

        if (m.issuedBook == null) {
            System.out.println("No book to return");
            return;
        }

        LocalDate today = LocalDate.now();

        if (today.isAfter(m.dueDate)) {
            long lateDays = ChronoUnit.DAYS.between(m.dueDate, today);
            System.out.println("Fine: ₹" + (lateDays * 10));
        } else {
            System.out.println("No Fine");
        }

        m.issuedBook.isIssued = false;
        m.issuedBook = null;
        m.issueDate = null;
        m.dueDate = null;

        System.out.println("Book Returned");
    }

    static void showBooks() {
        for (Book b : books) {
            String status = b.isIssued ? "Issued" : "Available";
            System.out.println(b.bookId + " " + b.title + " " + b.author + " " + status);
        }
    }

    static void removeBook() {
        System.out.print("Enter book id: ");
        int id = sc.nextInt();

        boolean removed = books.removeIf(b -> b.bookId == id);

        if (removed)
            System.out.println("Book Removed");
        else
            System.out.println("Book Not Found");
    }

    static void updateBook() {
        System.out.print("Enter book id: ");
        int id = sc.nextInt();

        Book b = findBook(id);

        if (b != null) {
            System.out.print("Enter new title and author: ");
            b.title = sc.next();
            b.author = sc.next();
            System.out.println("Book Updated");
        } else {
            System.out.println("Book Not Found");
        }
    }

    static void removeMember() {
        System.out.print("Enter member id: ");
        int id = sc.nextInt();

        boolean removed = members.removeIf(m -> m.memberId == id);

        if (removed)
            System.out.println("Member Removed");
        else
            System.out.println("Member Not Found");
    }

    static Member findMember(int id) {
        for (Member m : members) {
            if (m.memberId == id) return m;
        }
        return null;
    }

    static Book findBook(int id) {
        for (Book b : books) {
            if (b.bookId == id) return b;
        }
        return null;
    }
}
package CRUD;

import java.time.LocalDate;

class Member {
    int memberId;
    String name;
    Book issuedBook;
    LocalDate issueDate;
    LocalDate dueDate;

    Member(int id, String name, Book b, LocalDate i, LocalDate d) {
        this.memberId = id;
        this.name = name;
        this.issuedBook = b;
        this.issueDate = i;
        this.dueDate = d;
    }
}

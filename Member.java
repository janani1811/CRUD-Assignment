package CRUD;

import java.time.LocalDate;

class Member {
    int memberId;
    String name;
    Book issuedBook;
    LocalDate issueDate;
    LocalDate dueDate;
    
	public Member(int memberId, String name, Book issuedBook, LocalDate issueDate, LocalDate dueDate) {
		this.memberId = memberId;
		this.name = name;
		this.issuedBook = issuedBook;
		this.issueDate = issueDate;
		this.dueDate = dueDate;
	}
    
    
}
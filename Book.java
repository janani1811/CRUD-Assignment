package CRUD;

class Book {
    int bookId;
    String title;
    String author;
    boolean isIssued;

    Book(int id, String t, String a) {
        bookId = id;
        title = t;
        author = a;
        isIssued = false;
    }
}


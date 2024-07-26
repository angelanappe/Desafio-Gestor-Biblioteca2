package cl.praxis.demo.model.dto;

import java.time.LocalDate;

public class Loan {
    private Long id;
    private String userName;
    private Book book;
    private LocalDate returnDate;

    public Loan() {
    }

    public Loan(Long id, String userName, Book book) {
        this.id = id;
        this.userName = userName;
        this.book = book;
        this.returnDate = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}

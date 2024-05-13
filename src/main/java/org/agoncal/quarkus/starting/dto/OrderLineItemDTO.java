package org.agoncal.quarkus.starting.dto;


import org.agoncal.quarkus.starting.entity.Book;
import org.jboss.resteasy.annotations.ResponseObject;

public class OrderLineItemDTO {
    private Long bookId;
    private int  quantity;
    private Long id;

    private Book book;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public int getGetQuantity() {
        return quantity;
    }

    public void setGetQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}

package org.agoncal.quarkus.starting.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.agoncal.quarkus.starting.dto.BookDTO;

@Entity
@RegisterForReflection
public class Book extends PanacheEntity {

    @Column(nullable = false)
    private String title;
    private String author;

    @Column(name = "year_of_publication")
    private  int yearOfPublication;
    private String genre;


    public Book(Long bookId) {
    }

    public Book() {

    }

    public BookDTO builderDTO(){
       BookDTO result = new BookDTO();
       result.setId(this.id);
       result.setTitle(this.title);
       result.setAuthor(this.author);
        result.setGenre(this.genre);
        result.setYearOfPublication(this.yearOfPublication);
        return result;
    }

    public void builder(BookDTO dto){
        this.id = dto.getId();
        this.title = dto.getTitle();
        this.author = dto.getAuthor();
        this.yearOfPublication = dto.getYearOfPublication();
        this.genre = dto.getGenre();
    }

}

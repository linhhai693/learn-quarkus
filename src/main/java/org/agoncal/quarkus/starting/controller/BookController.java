package org.agoncal.quarkus.starting.controller;


import io.quarkus.hibernate.orm.panache.PanacheQuery;

import io.quarkus.security.Authenticated;
import io.quarkus.security.PermissionsAllowed;
import io.quarkus.security.spi.runtime.AuthorizationController;
import jakarta.annotation.security.RolesAllowed;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.agoncal.quarkus.starting.dto.BookDTO;
import org.agoncal.quarkus.starting.dto.CommonListDTO;
import org.agoncal.quarkus.starting.dto.DataFilterDTO;
import org.agoncal.quarkus.starting.entity.Book;
import org.agoncal.quarkus.starting.entity.user.Permission;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@Path("/api/books")
@RolesAllowed(Permission.Resource.BOOK)
@Produces(MediaType.APPLICATION_JSON)
public class BookController  {

    @POST
    @Transactional
    public Response  create(BookDTO bookDTO) {
        Book book = new Book();
        book.builder(bookDTO);
        book.persist();
        bookDTO = book.builderDTO();
        Response.ResponseBuilder response = Response.created(URI.create("/api/books/" + book.id));
        response.entity(bookDTO);
        return response.build();
    }
    @GET
    @Path("{id}")
    public BookDTO getBook(@PathParam("id") Long id){
        Book book = Book.findById(id);
        if(book == null){
            return null;
        }
        return book.builderDTO();
    }

    @GET
    public CommonListDTO<BookDTO> getAllBooks(@BeanParam DataFilterDTO dataFilterDTO){

        if(dataFilterDTO == null){
            return null;
        }
        int page = dataFilterDTO.getPage();
        int pageSize = dataFilterDTO.getSize();
        int fromIndex = (page - 1) * pageSize;

        List<Book> books;
        PanacheQuery<Book> query;

        if(dataFilterDTO.getKeyWords() == null || dataFilterDTO.getKeyWords().isEmpty()){
            query = Book.findAll();
        }else{
            query = Book.find("title =?1", dataFilterDTO.getKeyWords());
        }
        long total = query.count();
        int toIndex = Math.min((int)total, page * pageSize);
       // query.range(fromIndex, toIndex );
        books = query.list();

        List<Book> result = books.subList(fromIndex, toIndex);
        List<BookDTO> bookDTOs = result.stream().map(Book::builderDTO).collect(Collectors.toList());

        CommonListDTO<BookDTO> commonListDTO = new CommonListDTO<>();
        commonListDTO.setTotal((int)total);
        commonListDTO.setPage(page);
        commonListDTO.setSize(pageSize);
        commonListDTO.setValues(bookDTOs);

        return commonListDTO;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") int id){
        Book book = Book.findById(id);
        if(book == null){
            return null;
        }
        book.delete();
        return Response.noContent().build();
    }

}

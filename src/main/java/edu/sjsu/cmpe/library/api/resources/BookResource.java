package edu.sjsu.cmpe.library.api.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Review;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.util.CustomJsonJacson;
import edu.sjsu.cmpe.library.util.StatusCode;


@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
    public BookResource() {
    }
    
    @GET
    @Path("/{isbn}")
    @Timed(name = "view-book")
    public BookDto getBookByIsbn(@PathParam("isbn") LongParam isbn) 
    {
    	Book book;
        book = CustomJsonJacson.getFromHash(isbn.get());
    	BookDto bookResponse = new BookDto(book);
    	bookResponse.addLink(new LinkDto("view-book", "/books/" + book.getIsbn(),"GET"));
		bookResponse.addLink(new LinkDto("update-book","/books/" + book.getIsbn(), "POST"));
		return bookResponse;
    }
    
    @POST
    public Response createNewBookRec(Book book)                                                                                                                                                            
     {
    	long newISBN = book.getIsbn();
    	
        CustomJsonJacson.addIntoHash(newISBN, book);
        
        BookDto bookResponse = new BookDto(book);
        
        bookResponse.addLink(new LinkDto("view-book", "/books/" + newISBN,"GET"));
        bookResponse.addLink(new LinkDto("update-book","/books/" + newISBN, "PUT"));
        bookResponse.addLink(new LinkDto("delete-book","/books/" + newISBN, "DELETE"));
        bookResponse.addLink(new LinkDto("create-view","/books/" + newISBN, "POST"));
        
        return Response.status(201).entity(bookResponse).build();
    }
    
    @PUT
    @Path("/{isbn}/status={status}")
    public Response updateExistingBook(@PathParam("isbn") LongParam isbn, @PathParam("status") String status)
    {
    	
    	Book book = CustomJsonJacson.getFromHash(isbn.get());
    	long newISBN = isbn.get();
    	CustomJsonJacson.removeFromHash(isbn.get(), book);
    	book.setStatus(status);
    	
    	CustomJsonJacson.addIntoHash(isbn.get(), book);
        
        BookDto bookResponse = new BookDto(book);
        bookResponse.addLink(new LinkDto("view-book", "/books/" + newISBN,"GET"));
        bookResponse.addLink(new LinkDto("update-book","/books/" + newISBN, "PUT"));
        bookResponse.addLink(new LinkDto("delete-book","/books/" + newISBN, "DELETE"));
        bookResponse.addLink(new LinkDto("create-review","/book/" + newISBN +"/review", "POST"));
        bookResponse.addLink(new LinkDto("view-all-reviews","/book/" + newISBN+"/review", "POST"));
       
        ObjectMapper objM = new ObjectMapper();
        objM.configure(SerializationFeature.INDENT_OUTPUT, true);
        
        return Response.status(200).entity(bookResponse).build();
    }
    
    @POST
    @Path("/{isbn}/reviews")
    public Response createBookReview(@PathParam("isbn") LongParam isbn, Review review)
    {
    	
    	
    	if(review.getComment() == null || review.getComment() == "")
		{
			//throw new IllegalArgumentException("Enter a valid comment");
			return Response.ok(500).entity("Please Enter a valid Comment").build();
		}
    	
    	else if(review.getRating()<=5 && review.getRating()>=1)
    	{
	    	long newISBN = isbn.get();
	    	Review objReview = new Review(review.getComment(),review.getRating());
	    	
	    	Book book = CustomJsonJacson.getFromHash(isbn.get());
	    	book.addReview(objReview);
	    	
	    	BookDto bookResponse = new BookDto(book);
	        bookResponse.addLink(new LinkDto("view-book", "/books/" + newISBN,"GET"));
	        
	        return Response.ok(200).entity(bookResponse).build();
    	}
    	else 
    	{
    		//throw new IllegalArgumentException("Enter a valid rating");
			return Response.ok(500).entity("Please Enter a valid Rating").build();
    	
    	}
    }
    
    }




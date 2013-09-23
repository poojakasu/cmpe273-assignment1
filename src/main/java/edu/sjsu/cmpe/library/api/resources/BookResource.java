package edu.sjsu.cmpe.library.api.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Review;
import edu.sjsu.cmpe.library.domain.childAuthor;
import edu.sjsu.cmpe.library.domain.childReview;
import edu.sjsu.cmpe.library.dto.AuthorDto;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
import edu.sjsu.cmpe.library.dto.ReviewDto;
import edu.sjsu.cmpe.library.util.CustomJsonJacson;



@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
    public BookResource() {
    }
    
    @POST
    public Response createNewBookRec(Book book)                                                                                                                                                            
     {
    	long newISBN = book.getIsbn();
    	String isValid = book.validate();
    	
    	if(isValid == "true")
        {
    		book.addAuthorToHash();
    		CustomJsonJacson.addIntoHash(newISBN, book);
	    	
	        BookDto bookResponse = new BookDto();
	        
	        bookResponse.addLink(new LinkDto("view-book", "/books/" + newISBN,"GET"));
	        bookResponse.addLink(new LinkDto("update-book","/books/" + newISBN, "PUT"));
	        bookResponse.addLink(new LinkDto("delete-book","/books/" + newISBN, "DELETE"));
	        bookResponse.addLink(new LinkDto("create-review","/books/" + newISBN +"/reviews", "POST"));
	       
	        return Response.status(201).entity(bookResponse).build();
        }
    	else
    	{
    		try{
        		throw new WebApplicationException(new Throwable(isValid));
        	}
        	catch(WebApplicationException e){
        		
        	}
    		
    		//LinksDto links = new LinksDto();
    		//links.addLink(new LinkDto("create-book", "/books", "POST"));
    		
    		return Response.status(400).entity(isValid).build();
    	}
    }
    
    
    @GET
    @Path("/{isbn}")
    @Timed(name = "view-book")
    public Response getBookByIsbn(@PathParam("isbn") LongParam isbn) 
    {
    	Book book;
    	long newISBN = isbn.get();
        book = CustomJsonJacson.getFromHash(newISBN);
        if(book !=null){
    	BookDto bookResponse = new BookDto(book);
    	
    	 bookResponse.addLink(new LinkDto("view-book", "/books/" + newISBN,"GET"));
         bookResponse.addLink(new LinkDto("update-book","/books/" + newISBN, "PUT"));
         bookResponse.addLink(new LinkDto("delete-book","/books/" + newISBN, "DELETE"));
         bookResponse.addLink(new LinkDto("create-review","/books/" + newISBN +"/reviews", "POST"));
         if(book.getreview().size() > 0)
             bookResponse.addLink(new LinkDto("view-all-reviews","/books/" + newISBN +"/reviews", "GET"));
         
         CustomJsonJacson.jacksonToJson(bookResponse);
         
         return Response.status(200).entity(bookResponse).build();
        }
        else
        {
        	return Response.status(400).entity("Enter a valid Book Id").build();
        }
    }
    
    
    @DELETE
    @Path("/{isbn}")
    public Response deleteBookById(@PathParam("isbn") LongParam isbn){
    	
    	Book book;
    	
    	
    	book = CustomJsonJacson.getFromHash(isbn.get());
        
        if(book != null){
        	try{
        		CustomJsonJacson.removeFromHash(isbn.get(),book);
    	        BookDto bookResponse = new BookDto();
    	        bookResponse.addLink(new LinkDto("create-book","/books", "POST"));
    	        
    	    	return Response.ok(200).entity(bookResponse).build();
        	}
        	catch( Exception e){
        		return Response.ok(400).entity("Enter a valid book id").build();
        	}
        }
        else{
        	
        	return Response.ok(400).entity("Enter a valid Book Id").build();
        }	
       
    }
    
    
    @PUT
    @Path("/{isbn}")
    public Response updateExistingBook(@PathParam("isbn") LongParam isbn, @QueryParam("status") String status)
    {
    	if(correctStatus(status))
    	{
	    	Book book = CustomJsonJacson.getFromHash(isbn.get());
	    	
	    	if(book!=null){
	    	long newISBN = isbn.get();
	    	CustomJsonJacson.removeFromHash(isbn.get(), book);
    	
    		book.setStatus(status);
    	
	    	CustomJsonJacson.addIntoHash(isbn.get(), book);
	        
	        //BookDto bookResponse = new BookDto(book);//remove and add next line
	        BookDto bookResponse = new BookDto();
	        
	        bookResponse.addLink(new LinkDto("view-book", "/books/" + newISBN,"GET"));
	        bookResponse.addLink(new LinkDto("update-book","/books/" + newISBN, "PUT"));
	        bookResponse.addLink(new LinkDto("delete-book","/books/" + newISBN, "DELETE"));
	        bookResponse.addLink(new LinkDto("create-review","/books/" + newISBN +"/reviews", "POST"));
	        if(book.getreview().size() > 0)
	            bookResponse.addLink(new LinkDto("view-all-reviews","/books/" + newISBN +"/reviews", "GET"));
	       
	        return Response.status(200).entity(bookResponse).build();
	    	}
	    	else
	    		return Response.status(400).entity("Enter a valid Book Id").build();
    	}
    	else
    	{
    		return Response.status(400).entity("Invalid status. The status should be either of one: available,checked-out,in-queue,lost").build();
    	}
    	
    }
    
    public static boolean correctStatus(String status){
    	
        if(status.equals("available") || status.equals("checked-out") ||status.equals("in-queue") ||status.equals("lost") )
        	return true;
       return false;
    }
    
    @POST
    @Path("/{isbn}/reviews")
    public Response createBookReview(@PathParam("isbn") LongParam isbn, Review rev)
    {
    	if(rev.getcomment() == null || rev.getcomment() == "")
		{
			//throw new IllegalArgumentException("Enter a valid comment");
			return Response.ok(400).entity("Please Enter a valid Comment").build();
		}
    	
    	else if(rev.getrating()<=5 && rev.getrating()>=1)
    	{
	    	long newISBN = isbn.get();
	    	Review objReview = new Review();
	    	objReview.setid();
	    	objReview.setrating(rev.getrating());
	    	objReview.setcomment(rev.getcomment());
	    	
	    	Book book = CustomJsonJacson.getFromHash(isbn.get());
	    	
	    	book.addReview(objReview);
	    	book.addReviewToHash();
	    	
	    	 //BookDto bookResponse = new BookDto(book);//remove and add next line
	         BookDto bookResponse = new BookDto();
	    	 
	        bookResponse.addLink(new LinkDto("view-review", "/books/" + newISBN + "/reviews/"+ objReview.getid() ,"GET"));
	        
	        return Response.ok(201).entity(bookResponse).build();
    	}
    	else 
    	{
    		//throw new IllegalArgumentException("Enter a valid rating");
			return Response.ok(400).entity("Please Enter a valid Rating(from 1 to 5)").build();
    	
    	}
    }
    
    @GET
    @Path("/{isbn}/reviews/{id}")
    public Response getReviewById(@PathParam("isbn") LongParam isbn,@PathParam("id") Integer reviewId){
    	
    	Book book;
        book = CustomJsonJacson.getFromHash(isbn.get());
        ReviewDto bookResponse = null;
        if(book!=null){
        for(int i=0;i<book.getreview().size();i++){
	        if(book.getreview().get(i).getid().equals(reviewId))
	        {
		        childReview chdReview =  CustomJsonJacson.getReviewFromHash(reviewId);//book.getReviewById(reviewId);
		        bookResponse = new ReviewDto(chdReview);
		     	
		    	bookResponse.addLink(new LinkDto("view-review", "/books/" + isbn.get() + "/reviews/"+ reviewId ,"GET"));
	        }
        }
        }
        if(bookResponse !=null)
        	return Response.ok(200).entity(bookResponse).build();
        else
        	return Response.ok(400).entity("Enter a Valid Id").build();
    	
    }
    
    @GET
    @Path("/{isbn}/reviews")
    public Response getAllReview(@PathParam("isbn") LongParam isbn){
    	
    	Book book;
        book = CustomJsonJacson.getFromHash(isbn.get());
        if(book!= null){
        List<childReview> childReview = book.AllReview();
    	ReviewDto bookResponse = new ReviewDto(childReview);
    	
    	return Response.ok(200).entity(bookResponse).build();
        }
        else
        {
        	return Response.ok(400).entity("Enter valid Book id").build();
        }
    }
    
    @GET
    @Path("/{isbn}/authors/{id}")
    public Response getAuthorById(@PathParam("isbn") LongParam isbn,@PathParam("id") Integer authorID){
    	
    	Book book;
        book = CustomJsonJacson.getFromHash(isbn.get());
        AuthorDto bookResponse = null;
        if(book!= null){
        for(int i=0;i<book.getAuthors().size();i++){
        	Integer Checkid = book.getAuthors().get(i).IdForList();
	        if(Checkid.equals(authorID))
	        {
		        childAuthor objAuthor = CustomJsonJacson.getAuthFromHash(authorID);
		        bookResponse = new AuthorDto(objAuthor);
		    	
		    	bookResponse.addLink(new LinkDto("view-author", "/books/" + isbn.get() + "/authors/"+ authorID ,"GET"));
	        }
        }
        }
        if(bookResponse !=null)
        	return Response.ok(200).entity(bookResponse).build();
        else
        	return Response.ok(400).entity("Enter a valid id").build();
    }
    
    @GET
    @Path("/{isbn}/authors")
    public Response getAllAuthor(@PathParam("isbn") LongParam isbn){
    	
    	Book book;
        book = CustomJsonJacson.getFromHash(isbn.get());
        if(book!=null){
        List<childAuthor> objAuthor = book.gtAuthors();
        AuthorDto bookResponse = new AuthorDto(objAuthor);
    	return Response.ok(200).entity(bookResponse).build();
        }
        else
        {
        	return Response.ok(400).entity("Enter a valid Book Id").build();
        }
    }
    
   
    
    }




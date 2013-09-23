package edu.sjsu.cmpe.library.domain;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.api.resources.BookResource;
import edu.sjsu.cmpe.library.util.CustomJsonJacson;
import edu.sjsu.cmpe.library.util.CustomRandomGenerator;


public class Book {
	
    private long isbn;
    
    @NotEmpty
    @NotBlank
    @JsonProperty("title")
    private String title="";
    
    @NotEmpty
    @JsonProperty("publication-date")
    private String publicationdate="";
    
    private String language="";
    
    @JsonProperty("num-pages")
    private Long numpages=null;
    
    private String status = "available";
    
    private List<Review> review;
    private List<Author> authors;
    
    public String validate()
    { 
    	String retVal ="";
    	if(this.title == null || this.title == "null" || this.title == "")
    	{
    		return retVal = "Give a valid title for the book.";
    	}
    	else if(this.publicationdate == null ||  this.publicationdate.isEmpty() ){
    		return retVal = "Give a valid publication date for the book.";
            
        }
    	else if(!BookResource.correctStatus(this.status)){
    		return retVal = "Give a valid book status, "+this.status+" is not a valid status";
    	}
    	else if(this.authors.size() == 0)
    	{
    		return retVal = "Give a valid author details for the book.";
            	
    	}
    	else if(Author.validateauthor(this.authors)){
    		return retVal = "Give a valid author's name for the book.";
            
    	}
    	return "true";
    }
    
    public Book(){
    	review = new ArrayList<Review>();
    	this.isbn = CustomRandomGenerator.getRandomNumber();
    	}
   
    public long getIsbn() {
	return isbn;
    }

    public long setIsbn() {
	 //this.isbn = bookId.getRandomNumber();
	 return this.isbn;
    }

    public String gettitle() {
	return title;
    }

    public void settitle(String title)  {
    	this.title = title;
    }

	public String getpublicationdate() {
		return publicationdate;
	}

	public void setpublicationdate(String publication_date) {
		this.publicationdate = publication_date;
	}
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Long getnumpages() {
		return numpages;
	}

	public void setnumpages(Long num_pages) {
		this.numpages = num_pages;
	}

	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 * default status will be in available when the book is added
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public List<Review> getreview() {
		
		return this.review;
	}

	public void setReview(List<Review> rv) {
		this.review = rv;
	}
	
	public void addReview(Review rv){
		List<Review> oReview = this.getreview();
		oReview.add(rv);
		//childReview chdRev = new childReview(rv.getID(),rv.getComment(),rv.getRating()); 
		//CustomJsonJacson.addReviewIntoHash(rv.getID(), chdRev);
		this.setReview(oReview);
	}	
	
	/*public childReview getReviewById(Integer id)
	{	
		for(int i =0;i<this.review.size();i++)
		{
			if(this.review.get(i).getID() == id)
				return CustomJsonJacson.getReviewFromHash(id); //this.review.get(i);
		}	
		return null;
	}*/
	
	public List<childReview> AllReview(){
		List<childReview> chdReview = new ArrayList<childReview>();
		for(int i =0;i<this.review.size();i++)
		{
			Integer reviewid = review.get(i).getid();
			chdReview.add(CustomJsonJacson.getReviewFromHash(reviewid));
		}	
		return chdReview;
	}
	
	public List<Author> getAuthors(){
		return this.authors;
	}
	
	public List<childAuthor> gtAuthors() {
		List<childAuthor> childAuthor = new ArrayList<childAuthor>();
		for(int i =0;i<this.authors.size();i++)
		{
			Integer authid = authors.get(i).IdForList();
			childAuthor.add(CustomJsonJacson.getAuthFromHash(authid));
		}	
		return childAuthor;
	}

	public void setAuthors(List<Author> auth) {
		for(int i =0;i<auth.size();i++)
			{
				auth.get(i).setbookid(this.isbn);
				auth.get(i).setId();
				auth.get(i).setHref();
				//childAuthor chdAuth = new childAuthor(auth.get(i).id,auth.get(i).name);
				//CustomJsonJacson.addAuthIntoHash(auth.get(i).id, chdAuth);
		        
			}
		this.authors = auth;
	}
	
	
	public void addReviewToHash()
	{
		for(int i =0;i<this.review.size();i++)
		{
			childReview chdRev = new childReview(this.review.get(i).getid(),this.review.get(i).getcomment(),this.review.get(i).getrating()); 
			CustomJsonJacson.addReviewIntoHash(this.review.get(i).getid(), chdRev);
		}
	}
	public void addAuthorToHash()
	{
		for(int i =0;i<this.authors.size();i++)
		{
			childAuthor chdAuth = new childAuthor(this.authors.get(i).id,this.authors.get(i).name);
			CustomJsonJacson.addAuthIntoHash(this.authors.get(i).id, chdAuth);
	        
		}
	}
	
	public Author getAuthorById(Integer id){
		
		for(int i =0;i<this.authors.size();i++)
		{
			if(this.authors.get(i).IdForList() == id)
				return this.authors.get(i);
		}	
		return null;
	}
}

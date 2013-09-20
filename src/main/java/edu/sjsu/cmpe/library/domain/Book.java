package edu.sjsu.cmpe.library.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import edu.sjsu.cmpe.library.util.CustomRandomGenerator;
import edu.sjsu.cmpe.library.util.StatusCode;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder(alphabetic=false)
public class Book {
    private long isbn;
    private String title="";
    
    @JsonProperty("publication-date")
    private String publicationdate="";
    
    private String language="";
    
    @JsonProperty("num-pages")
    private Long numpages=null;
    
    private String status = StatusCode.BOOK_AVAILABLE;
    
    private List<Review> review;
 
    private List<Author> authors;
    
    CustomRandomGenerator bookId;
    
    public Book(){
    	bookId = new CustomRandomGenerator();
    	review = new ArrayList<Review>();
    	this.isbn = bookId.getRandomNumber();
    }
   
    public long getIsbn() {
	return isbn;
    }

    public long setIsbn() {
	 //this.isbn = bookId.getRandomNumber();
	 return this.isbn;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
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

	public List<Review> getReview() {
		return review;
	}

	public void setReview(List<Review> rv) {
		this.review = rv;
	}
	
	public void addReview(Review rv){
		List<Review> oReview = this.getReview();
		oReview.add(rv);
		this.setReview(oReview);
	}	
	
	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> auth) {
		for(int i =0;i<auth.size();i++)
			{
				auth.get(i).setbookid(this.isbn);
				auth.get(i).setId();
			}
		this.authors = auth;
	}
}

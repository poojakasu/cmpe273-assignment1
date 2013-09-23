package edu.sjsu.cmpe.library.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.Book;

@JsonPropertyOrder(alphabetic = true)
@JsonInclude(Include.NON_NULL)
public class BookDto extends LinksDto {
     Book book;

    public BookDto() {
    	super();
    	
        }
    
    public BookDto(Book book) {
	super();
	this.book = book;
    }
    
    public void setBook(Book book){ this.book = book;}
    public Book getBook(){return this.book;}

}

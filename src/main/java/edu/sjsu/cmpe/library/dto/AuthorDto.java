package edu.sjsu.cmpe.library.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.Author;

@JsonPropertyOrder(alphabetic = true)
public class AuthorDto extends LinksDto {
    private Author author;

    /**
     * @param book
     */
    public AuthorDto() {
    	super();
    	
        }

    public AuthorDto(Author a) {
	super();
	this.setAuthor(a);
    }

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

   
}

package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.childAuthor;
import edu.sjsu.cmpe.library.domain.childReview;

@JsonPropertyOrder(alphabetic = true)
public class AuthorDto extends LinksDto {
    //childAuthor chdauthor;
    @JsonProperty("Authors")
    @JsonInclude(Include.NON_NULL)
    private List<childAuthor> lstAuthor;

    public AuthorDto() {
    	super();
    	}

    public AuthorDto(childAuthor a) {
	super();
	lstAuthor = new ArrayList<childAuthor>();
	this.lstAuthor.add(a);
    }
    
    public AuthorDto( List<childAuthor> lstAuthor) {
    	super();
    	this.setlstAuthor(lstAuthor);
     }

	/*public childAuthor getAuthor() {
		return this.chdauthor;
	}

	public void setAuthor(childAuthor author) {
		this.chdauthor = author;
	}
    */
	public List<childAuthor> getlstAuthor()
    {
    return this.lstAuthor;
    }
	
	public void setlstAuthor(List<childAuthor> author) {
		this.lstAuthor = author;
	}
   
}

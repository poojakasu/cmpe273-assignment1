package edu.sjsu.cmpe.library.domain;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.util.CustomRandomGenerator;


public class Review {
    
	private Integer id =0;
    @JsonProperty("rating")
    Integer rating=0;
    
	@JsonProperty("comment")
    private String comment="";
    
    
    public Review(){
    	
    }
    
    /*public Review(String Comment, Integer Rating)
    {
    	this.Comment = Comment;
    	this.Rating = Rating;
    	ID= CustomRandomGenerator.getRandomNumber();
    }*/
   
	public void setid() {
		if(this.id == 0)
			this.id = CustomRandomGenerator.getRandomNumber();
	}
	
	public void setrating(Integer Rating) {
	    this.rating = Rating;
	}
	
	public Integer getid() {
		return this.id;
	}
	public String getcomment() {
		return this.comment;
	}
	
	public Integer getrating() {
		return this.rating;
	}
	public void setcomment(String obj) {
		this.comment = obj;
	}
}

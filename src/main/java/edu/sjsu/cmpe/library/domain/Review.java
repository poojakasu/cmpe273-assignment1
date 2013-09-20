package edu.sjsu.cmpe.library.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.util.CustomRandomGenerator;
@JsonPropertyOrder(alphabetic=false)
public class Review {
    private Integer ID =0;
    
    private Integer Rating;
    private String Comment;
    
    CustomRandomGenerator ReviewId;
    
    public Review(){
    	
    }
    public Review(String comment, Integer rating)
    {
    	ReviewId = new CustomRandomGenerator();
    	Comment = comment;
    	Rating = rating;
    	ID= ReviewId.getRandomNumber();
    }
	public Integer getID() {
		return ID;
	}
	public void setID() {
		ID = ReviewId.getRandomNumber();
	}
	public Integer getRating() {
		return Rating;
	}
	public void setRating(Integer rating) {
	    Rating = rating;
	}
	public String getComment() {
		return Comment;
	}
	public void setComment(String comment) {
		Comment = comment;
	}
}

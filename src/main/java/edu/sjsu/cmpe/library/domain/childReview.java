package edu.sjsu.cmpe.library.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class childReview{
	
	private String comment;
	private Integer rating;
	private Integer id;
	
	public childReview(Integer oId, String oComment,Integer oRating){
		this.comment = oComment;
		 this.id = oId;
		 this.rating = oRating;
	}
	
	public String getComment(){
		return this.comment;
	}
	public Integer getRating(){
		return this.rating;
	}
	
	public Integer getid()
	{
		return this.id;
	}
	
	public void setcomment(String comm){
		this.comment = comm;
	}
	
	public void setid(Integer oid){
		   this.id = oid;
		}
	
	public void setrating(Integer oRating){
		   this.rating = oRating;
		}
	
}

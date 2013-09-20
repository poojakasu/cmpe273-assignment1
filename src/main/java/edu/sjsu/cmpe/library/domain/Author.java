package edu.sjsu.cmpe.library.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.util.CustomRandomGenerator;

@JsonPropertyOrder(alphabetic=false)
public class Author {
	
	
	Integer id = 0;
    String name;
    long bookid;
    CustomRandomGenerator authorId;
    private String rel = "self"; // default is 'self'
    private String href = "/"; // default is '#'
    private String method = "GET"; // default is 'GET'

    
    public Author(){
    	authorId = new CustomRandomGenerator();
    	this.id = authorId.getRandomNumber();
    	
    	setRel("view-author");
    	setHref("/books/"+ this.bookid+"/authors/" + this.id);
    	setMethod("GET");
    }
    
    /*public Author(String n)
    {
    	authorId = new CustomRandomGenerator();
    	this.name = n;
    	this.id = authorId.getRandomNumber();
    }*/
    
   /* public String getName()
    {
    	return name;
    }*/
    
    public void setName(String n)
    {
    	name = n;
    }
    
    
    
    public void setbookid(long bId){
    	this.bookid = bId;
    }
    
	public void setId() {
		//this.id = authorId.getRandomNumber();
	}
	
    public Author(String rel, String href, String method) {
	super();
	this.rel = rel;
	this.href = href;
	this.method = method;
    }

  
    public String getRel() {
	return rel;
    }

 
    public void setRel(String rel) {
	this.rel = rel;
    }

    public String getHref() {
	return href;
    }

    public void setHref(String href) {
	this.href = href;
    }

    
    public String getMethod() {
	return method;
    }

    
    public void setMethod(String method) {
	this.method = method;
    }

}

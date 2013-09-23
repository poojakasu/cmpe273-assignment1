package edu.sjsu.cmpe.library.domain;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.util.CustomRandomGenerator;

@JsonPropertyOrder(alphabetic=false)
public class Author {
	
	
	Integer id = 0;
	
	@NotEmpty
	String name;
	
    long bookid;
    
    private String rel = "self"; // default is 'self'
    private String href = "/"; // default is '#'
    private String method = "GET"; // default is 'GET'

    public static boolean validateauthor(List<Author> arrAuth)
    {
    	for(int i=0;i<arrAuth.size();i++)
    	{
    		if(arrAuth.get(i).name == null || arrAuth.get(i).name.isEmpty())
    			return true;// if something is empty
    	}
    	return false;
    	
    }
    public Author(){
    	//authorId = new CustomRandomGenerator();
    	this.id = CustomRandomGenerator.getRandomNumber();
    	setRel("view-author");
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
    
    public Integer IdForList()
    {
       return id;
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

    public void setHref() {
    	this.href ="/books/"+ this.bookid+"/authors/" + this.id;
    }

    
    public String getMethod() {
	return method;
    }

    
    public void setMethod(String method) {
	this.method = method;
    }

}

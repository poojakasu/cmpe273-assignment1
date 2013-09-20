package edu.sjsu.cmpe.library.util;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;






import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.sjsu.cmpe.library.api.resources.BookCollection;
import edu.sjsu.cmpe.library.domain.Book;
	
@JsonIgnoreProperties(ignoreUnknown=true)

public class CustomJsonJacson {
	
	public static ObjectMapper jsonMapper = new ObjectMapper();
	public static File inputFile = new File("books.json");
	public static Map<Long,Book> objHashBook = new HashMap<Long,Book>();
	
    
	public static void addIntoHash(Long objISBN,Book obj ){
	    objHashBook.put(objISBN,obj);
	}
	
	public static void removeFromHash(Long objISBN,Book book){
		
	    objHashBook.remove(objISBN, book);
	}
	
    public static Book getFromHash(Long objISBN)
    {
    	return objHashBook.get(objISBN);
    }
	public static void jacksonToJson(Book obj) {
	try {
		jsonMapper.writeValue(inputFile, obj);
        //return jsonMapper.writeValueAsString(obj);
    } catch (IOException e) {
        throw new RuntimeException("Error serializing to json", e);
    	}
	}

	
	public static Book BookObjFromJson(String json) {
	    try {
	    	
	        return jsonMapper.readValue(json, Book.class);
	    } catch (IOException e) {
	        throw new RuntimeException("Had a problem deserializing", e);
	    }
	}
	
	//GET
	public static BookCollection jacksonFromJson(String json) {
		
		try {
		        return jsonMapper.readValue(json,BookCollection.class); 
		  	} catch (Exception e) {
		  		throw new RuntimeException("Had a problem deserializing", e);	
		}
		
	}
}

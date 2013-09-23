package edu.sjsu.cmpe.library.domain;

public class childAuthor {

	private String name;
	private Integer id;
	
	public childAuthor(Integer oId, String oName){
		this.name = oName;
		 this.id = oId;
	}
	
	public void setName(String oName){
	   this.name = oName;
	}
	public String getName(){
		return name;
	}
	
	public void setid(Integer oid){
		   this.id = oid;
		}
	public Integer getid(){
		return id;
	}
}

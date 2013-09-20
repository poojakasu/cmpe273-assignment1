package edu.sjsu.cmpe.library.util;

import java.util.Random;

public class CustomRandomGenerator {
	
	public Integer nextRandomNo = 0;
	Random objRandom = new Random();
	
	public CustomRandomGenerator()
	{
		
	}
	
	public Integer getRandomNumber()
	{
		return objRandom.nextInt();
	}

}

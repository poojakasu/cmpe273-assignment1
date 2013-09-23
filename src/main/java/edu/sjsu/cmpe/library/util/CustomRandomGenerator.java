package edu.sjsu.cmpe.library.util;

import java.util.Random;

public class CustomRandomGenerator {
	
	static Random objRandom = new Random();
	
	public CustomRandomGenerator()
	{
		
	}
	
	public static Integer getRandomNumber()
	{
		return objRandom.nextInt(1000);
	}

}

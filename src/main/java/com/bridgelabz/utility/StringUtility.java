package com.bridgelabz.utility;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class StringUtility {
	
	private static SecureRandom random = new SecureRandom();
	private static Random rand=new Random();
	

	
	  public static String nextSessionId() {
	    return new BigInteger(130, random).toString(32);
	  }
	  
	  
	  public static int randInt(int min,int max) {
		    int randomNum = rand.nextInt((max - min) + 1) + min;
		    return randomNum;
		}

}

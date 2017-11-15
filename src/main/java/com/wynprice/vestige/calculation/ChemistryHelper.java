package com.wynprice.vestige.calculation;

public class ChemistryHelper 
{
	public static int HCF(int a, int b)
	{
	    while (a != b) 
	    	if (a > b) a -= b;
	        else b -= a;
	    return a;
	}

	public static int LCM(int a, int b)
	{
	    return (a * b) / HCF(a, b);
	}
	
	public static String getSubscript(int subscript)
	{
		String finalString = "";
		for(char c : String.valueOf(subscript).toCharArray())
			switch (c) {
			case '0': finalString += "\u2080";
			case '1': finalString += "\u2081";
			case '2': finalString += "\u2082";
			case '3': finalString += "\u2083";
			case '4': finalString += "\u2084";
			case '5': finalString += "\u2085";
			case '6': finalString += "\u2086";
			case '7': finalString += "\u2087";
			case '8': finalString += "\u2088";
			case '9': finalString += "\u2089";
			}
		return finalString;
	}
}

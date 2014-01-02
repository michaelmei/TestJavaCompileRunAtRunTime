package com.pat.example4;

public class MyRunTimeClass {
	public MyRunTimeClass()
	{
		System.out.println("ctor:no values passed");
	}
	
	public MyRunTimeClass(String s)
	{
		System.out.println("ctor with string");
		snam = s;
		
	}
	private String snam="NOTSET";
	public String Set(String snam)
	{
		System.out.println("stringin"+snam);
		this.snam = snam;
		return this.snam;
	}
	
	public String Get()
	{
		System.out.println("stringout"+snam);
		return this.snam;
	}
}

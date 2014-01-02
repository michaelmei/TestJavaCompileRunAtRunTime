package com.pat.compilerun;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class TestCompileRunAtRunTime {
	
	public static void main(String[] args) {
		System.out.println("The example compile and load and test a java file at run time");
		
		// Step 1: Load the Java file and compile and build a class file
		try {
			JCompiler.CompileJava("/Users/mpatalberta/testjava/");
		} catch (IOException e) {
			System.out.println("ERROR:"+e.getMessage());
			e.printStackTrace();
			return;
		}
	    Class myruntimeclass = null;
		// Step 2: Load the class
		 ClassLoader parentClassLoader = MyClassLoader.class.getClassLoader();
		    MyClassLoader classLoader = new MyClassLoader(parentClassLoader
		    		,"/Users/mpatalberta/testjava/MyRunTimeClass.class");
		    myruntimeclass = null;
		    try {
				myruntimeclass = classLoader.loadClass("com.pat.example4.MyRunTimeClass");
			
		    } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				e.printStackTrace();
			}	
		// Step 3: Test the class file

			
		    if(myruntimeclass != null)
		    {
		    	System.out.println("able to load the class");
				// create the object with the default constructor
				
				Object myruntimeclassobj4 = null;

		       try {
				myruntimeclassobj4 = myruntimeclass.newInstance();
				// object constructed now find a set method with parameter of a string
					Class c;
					c = myruntimeclassobj4.getClass();
					String s1;
					
					s1 = doSet(c,myruntimeclassobj4,"hellopatty");
					System.out.println("afterset>"+s1+"<");
					s1 = doGet(c,myruntimeclassobj4);
					System.out.println("afterget>"+s1+"<");
					
		       } catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		       } catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		       }
		    }
		    
		    // try another loading
			try {
				String s1;
				Class c;
				Object myruntimeclassobj5 = null;
				myruntimeclassobj5 = myruntimeclass.getConstructor(String.class).newInstance("Hello Pat");
				c = myruntimeclassobj5.getClass();
				
				s1 = doGet(c,myruntimeclassobj5);
				System.out.println("afterget>"+s1+"<");
				
				s1 = doSet(c,myruntimeclassobj5,"hellopatty");
				System.out.println("afterset>"+s1+"<");
				
				s1 = doGet(c,myruntimeclassobj5);
				System.out.println("afterget>"+s1+"<");
				
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Exiting");
		    
	}

	public static String doGet(Class c, Object obj) {
		String sRes =null;
		Method[] m;
		m = c.getMethods();
		for(int i = 0;i<m.length;i++)
		{	

            	if(m[i].getName().matches("Get"))
            	{
						try {
							sRes = (String)m[i].invoke(obj);
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
            	}

		}
		return sRes;
	}

	public static String doSet(Class c,Object obj ,String s)
	{
		String sRes =null;
		Method[] m;
		m = c.getMethods();
		for(int i = 0;i<m.length;i++)
		{	

            	if(m[i].getName().matches("Set"))
            	{
						try {
							sRes = (String)m[i].invoke(obj, "hellopatty");
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
            	}

		}
		
		return sRes;
	}	
	
}

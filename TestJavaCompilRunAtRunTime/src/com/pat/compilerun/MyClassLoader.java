package com.pat.compilerun;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;

public class MyClassLoader extends ClassLoader{
	String pathwithclass = "file:C:/data/projects/tutorials/web/WEB-INF/" +"classes/reflection/MyObject.class";
    String pathclass = "reflection.MyObject";
	public MyClassLoader(ClassLoader parent,String pathwithclass) {
        super(parent);
        this.pathwithclass = pathwithclass;
    }

    public Class loadClass(String name) throws ClassNotFoundException {
        if(!name.equals(name))
                return super.loadClass(name);

        try {
            String url = "file://"+pathwithclass;

            URL myUrl = new URL(url);
            URLConnection connection = myUrl.openConnection();
            InputStream input = connection.getInputStream();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int data = input.read();

            while(data != -1){
                buffer.write(data);
                data = input.read();
            }

            input.close();

            byte[] classData = buffer.toByteArray();

            Class result;
            try 
            { 
              result = super.findSystemClass(name); 
              System.out.println(" >>>>>> returning system class (in CLASSPATH)."); 
              return result; 
                
            } catch (ClassNotFoundException e) { 
                         System.out.println("        >>>>>> Not a system class."); 
             }  
            
            /* Try to load it from our repository */ 

            
            
            return defineClass(name,
                    classData, 0, classData.length);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace(); 
        }

        return null;
    }
    
    public byte getClassImplFromDataBase(String className)[] {
        System.out.println("        >>>>>> Fetching the implementation of " +
                           className);
        byte result[];
        try {
            FileInputStream fi = new FileInputStream("." +
                    className + ".class");
            result = new byte[fi.available()];
            fi.read(result);
            return result;
        } catch (Exception e) {
 
            return null;
        }
    }
    
	// dynamically load a jar file
	static public Class LoadClassFromJar(String pathtojar,String myclass)
	{
		
	
		File file  = new File(pathtojar);
		URL url=null;
		try {
			url = file.toURL();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		URL[] urls = new URL[]{url};
		ClassLoader cl = new URLClassLoader(urls);

		Class cls = null;
		try {
			cls = cl.loadClass(myclass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	 return cls;
	}

}

package com.transportnswinfo.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

//this is parent class
public class TestBase {
	//Declaring WebDriver and Properties as static
	public static WebDriver driver;
	static Properties prop;
	
	
	//Constructor
	public TestBase() throws IOException{
	
		
	//try catch block	
	try{
		String  testfile="C:\\Users\\cheta\\workspace_Luna_Selenium\\Exercise\\src\\main\\java\\configuration";
//	InputStream input=new FileInputStream("./Exercise/src/main/java/configuration/Config.properties");
	
	InputStream input=new FileInputStream(testfile);
	System.out.println("the given file path: "+testfile);
	//C:\\Users\\cheta\\workspace_Luna_Selenium\\Exercise\\src\\main\\java\\configuration
	
	
	//C:\Users\cheta\workspace_Luna_Selenium\Exercise\drivers
	
	Properties prop=new Properties();
	prop.load(input);
		
	}
	catch(FileNotFoundException e)
	{
		System.out.println(" File not found, please check the file path" +e);
		
	}
	}
	
	//This method initialize the browser,
	//set implicitlywait and pageLoadTimeout and launching the AUT using the getProperty of prop
	public static void initialize()
	{
	
		/*String browser=prop.getProperty("browser");
		
		if(browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver","./drivers/ChromeDriver");
			driver=new ChromeDriver();
		}
		*/
		System.setProperty("webdriver.chrome.driver","C:\\Users\\cheta\\workspace_Luna_Selenium\\Exercise\\drivers\\chromedriver.exe");
		driver=new ChromeDriver();
		
			driver.manage().window().maximize();
			
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			
			driver.get(prop.getProperty("url"));
		}
	}


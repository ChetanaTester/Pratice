package com.transportnswinfo.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.transportnswinfo.base.TestBase;

public class TripPlanList {

	public TripPlanList() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	WebDriver driver = null;

	@BeforeClass
	public void setUp() {

		System.setProperty("webdriver.chrome.driver",
				"./drivers/chromedriver.exe");

	}
@Test
	public void pageLoad() throws IOException {
		
		driver = new ChromeDriver();

		InputStream input=new FileInputStream("C:/Users/cheta/workspace_Luna_Selenium/Exercise/src/main/java/com.transportnswinfo.configuration/Config.properties");
		
		Properties prop=new Properties();
		prop.load(input);
		
		String url=prop.getProperty("url");
		
	
		driver.get(url);
		driver.manage().window().maximize();

		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		String title = driver.getTitle();
		System.out.println("The page tilte : " + title);

		Assert.assertEquals(title, "Trip Planner | transportnsw.info");

	}
@Test
	public void tripPlan() {

		
		WebElement pointA=driver.findElement(By.id("search-input-From"));
		pointA.sendKeys(" North Sydney Station,North Sydney");

		WebElement pointB=driver.findElement(By.id("search-input-To"));
		pointB.sendKeys("Town Hall Station,Sydney ");

		WebElement Choice=driver.findElement(By.xpath("//span[@class='time-options-trunc ng-binding'][contains(text(),'Leaving now')]"));
		Choice.click();

	
		WebDriverWait Go = new WebDriverWait(driver, 300);
		Go.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("search-button")))).click();

		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

		// Block containing No. of Travel
		
		 List<WebElement>TList=driver.findElements(By.cssSelector("div[role='listitem']"));
		 int Tsize=TList.size();
		  System.out.println("No. of travel times avaiable:"+Tsize);
		  
		 
		  if(Tsize>0){
			  
			  System.out.println("The results are displayed");
		  }else{
			  System.out.println("No result");
		  }
		  	
		  	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		//getName provides the test method name
		String tName = result.getName();
		System.out.println("Invoking Teardown for Test = "+tName);
		if(result.getStatus()==ITestResult.FAILURE)
		{
			System.out.println("Test Case "+tName+" FAILED");
		} else {
			System.out.println("Test Case "+tName+" PASSED");
		}
		
	}
	
	@AfterClass
	public void closeBroswer() {
		driver.close();
	}





}

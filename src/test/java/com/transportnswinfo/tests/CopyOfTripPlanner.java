package com.transportnswinfo.tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CopyOfTripPlanner {

	WebDriver driver = null;

	@BeforeClass
	public void setUp() {

		System.setProperty("webdriver.chrome.driver",
				"./drivers/chromedriver.exe");

	}

	@Test(priority = 1)
	public void pageLoad() {

		driver = new ChromeDriver();

		driver.get("https://transportnsw.info/trip");
		driver.manage().window().maximize();

		driver.manage().timeouts().pageLoadTimeout(50,TimeUnit.SECONDS);
		String title = driver.getTitle();
		System.out.println("The page tilte : " + title);

		Assert.assertEquals(title, "Trip Planner | transportnsw.info");

	}

	@Test(priority = 2)
	public void tripPlan() {

		WebElement From = driver.findElement(By.id("search-input-From"));
		From.sendKeys(" North Sydney Station,North Sydney");

		WebElement To = driver.findElement(By.id("search-input-To"));
		To.sendKeys("Town Hall Station,Sydney ");

		WebElement choice = driver
				.findElement(By
						.xpath("//span[@class='time-options-trunc ng-binding'][contains(text(),'Leaving now')]"));
		choice.click();

	
		WebDriverWait Go = new WebDriverWait(driver, 300);
		Go.until(
				ExpectedConditions.visibilityOf(driver.findElement(By
						.id("search-button")))).click();

		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

		// Block containing No. of Travel
		
		 List<WebElement>TList=driver.findElements(By.cssSelector("div[role='listitem']"));
		 int Tsize=TList.size();
		  System.out.println("No. of travel times avaiable:"+Tsize);
		 boolean flag2=false;
		  if(Tsize>0){
			  
			  System.out.println("The results are displayed");
			  flag2=true;
		  }else{
			  System.out.println("No result");
		  }
		  
		  
		  if(flag2==true){
		  System.out.println("Results are as follows: ");
		  	for(int s=0;s<TList.size();s++)
		  	{
		 
		  		WebElement tpresult = driver.findElement(By.xpath("*//div[@class='tp-result-item-timing']"));

		  		System.out.println("--------------------------------------------------------------------------");

		
		  		// Getting routeno like T1..
		  		String routeno = driver.findElement(By.xpath("//span[@class='tp_train-route-number-label']")).getText();
		  		System.out.println(routeno);

		  		// Getting Departure and Arrival time
		  		String deptime = driver.findElement(By.xpath("//span[@class='departure-time ng-binding']")).getText();
			
		  		String arrtime = driver.findElement(By.xpath("//span[@class='arrival-time ng-binding']")).getText();

		  		// Getting Duration of travel
		  		String duration = driver.findElement(By.xpath("//span[@class='tp-result-item-timing-duration ng-binding']")).getText();

		  		// Printing them
		  		System.out.println("Departure time> " + deptime + " "+ " Arrival time >" + arrtime);
		  		System.out.println(duration);

					
				WebElement acc=driver.findElement(By.xpath("//li[@ng-if='tripSummaryVm.isAccessible'][@class='ng-scope']"));
		
				String acctext = driver.findElement(By.xpath("//li/span[@class='sr-only']")).getText();
				System.out.println("Mobility service: " +acctext);

				String opal= driver.findElement(By.xpath("//span[@class='opal-fare ng-binding']")).getText();
				System.out.println("The opal fare: " + opal);
				System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

		  	}

		  	}
	}
}
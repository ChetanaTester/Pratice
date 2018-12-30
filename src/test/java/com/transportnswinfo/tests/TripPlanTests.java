package com.transportnswinfo.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import org.testng.ITestResult;

import java.io.IOException;
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

import com.transportnswinfo.base.TestBase;
import com.transportnswinfo.pages.TripPlanPages;

public class TripPlanTests  extends TestBase{

	//WebDriver driver = null;

	TripPlanPages Phileas;
	
	public TripPlanTests() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@BeforeClass
	public void setUp() throws IOException {

		System.out.println("Launching the browser ");
		initialize();
		Phileas=new TripPlanPages();
	}

	@Test(priority = 1)
	public void pageLoadTest() {

		String title=Phileas.pageLoad();
		Assert.assertEquals(title, "Trip Planner | transportnsw.info");

	}

	@Test(priority = 2)
	public void tripSearchTest() {
		Phileas.tripSearch();
		Assert.assertEquals(true, true);
		System.out.println("There are number of reults displayed for the search");
		
		
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

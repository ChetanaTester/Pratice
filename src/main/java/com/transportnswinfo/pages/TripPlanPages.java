package com.transportnswinfo.pages;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.transportnswinfo.base.TestBase;

public class TripPlanPages extends TestBase {
	private String title;
	public static String pagetitle;

	public TripPlanPages() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * 
	 * By From = By.id("search-input-From");
	 * 
	 * By To = By.id("search-input-To");
	 * 
	 * By choice = By.xpath(
	 * "//span[@class='time-options-trunc ng-binding'][contains(text(),'Leaving now')]"
	 * );
	 * 
	 * 
	 * By search_button=By.id("search-button");
	 */

	public String pageLoad() {

		pagetitle = driver.getTitle();
		System.out.println("The page tilte : " + pagetitle);

		String pagesource = driver.getPageSource();
		System.out.println("The pagesource: " + pagesource);
		return pagetitle;
	}

	public void tripSearch() {

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
		List<WebElement> TList = driver.findElements(By
				.cssSelector("div[role='listitem']"));
		int Tsize = TList.size();
		System.out.println("No. of travel times avaiable:" + Tsize);
		boolean flag2 = false;
		if (Tsize > 0) {

			System.out.println("Search successfull");
			flag2 = true;
		} else {
			System.out.println("No result");
		}
		
		if(flag2==true){
			
		
		System.out.println("No. of travel times avaiable:" + Tsize);
		for (int s = 0; s < Tsize; s++) {

			WebElement tpresult = driver.findElement(By
					.xpath("*//div[@class='tp-result-item-timing']"));

			System.out
					.println("--------------------------------------------------------------------------");

			// Getting routeno like T1..
			String routeno = driver.findElement(
					By.xpath("//span[@class='tp_train-route-number-label']"))
					.getText();
			System.out.println(routeno);

			// Getting Departure and Arrival time
			String deptime = driver.findElement(
					By.xpath("//span[@class='departure-time ng-binding']"))
					.getText();

			String arrtime = driver.findElement(
					By.xpath("//span[@class='arrival-time ng-binding']"))
					.getText();

			// Getting Duration of travel
			String duration = driver
					.findElement(
							By.xpath("//span[@class='tp-result-item-timing-duration ng-binding']"))
					.getText();

			// Printing them
			System.out.println("Departure time> " + deptime + " "
					+ " Arrival time >" + arrtime);
			System.out.println(duration);

			WebElement acc = driver
					.findElement(By
							.xpath("//li[@ng-if='tripSummaryVm.isAccessible'][@class='ng-scope']"));

			String acctext = driver.findElement(
					By.xpath("//li/span[@class='sr-only']")).getText();
			System.out.println("Mobility service: " + acctext);

			String opal = driver.findElement(
					By.xpath("//span[@class='opal-fare ng-binding']"))
					.getText();
			System.out.println("The opal fare: " + opal);
			System.out
					.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		}
		}
	}

}

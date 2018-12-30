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

public class TripPlanner {

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

		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
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

		/*
		 * //Clicking on options button WebElement
		 * options=driver.findElement(By.
		 * xpath("//div/journey-options/button[@type='button']"));
		 * options.click();
		 * 
		 * 
		 * //Trip options document
		 * 
		 * //1. Modes of transport section:
		 * 
		 * System.out.println("Modes of transport to choose are :");
		 * 
		 * List<WebElement> modes=driver.findElements(By.cssSelector(
		 * "div[class='tp-option-select checkbox checked']")); //row
		 * tp-search-modes System.out.println("Modes list size:"+modes.size());
		 * System.out.println("Modes picked by default are: "); for(int
		 * i=0;i<modes.size();i++){ WebElement we=modes.get(i); String
		 * m=we.getText(); System.out.println(">> " +m); }
		 * 
		 * //Opal services only section:
		 * 
		 * System.out.println("************opal services only*************");
		 * WebElement
		 * opal=driver.findElement(By.id("opalServicesSelectTickTitle-label"));
		 * if(opal.isSelected()) {
		 * System.out.println("Opal Service: "+opal.getText()); } else{
		 * System.out.println("The option is not selected"); }
		 * 
		 * 
		 * //3.Mobility section:
		 * System.out.println("************Mobility *************");
		 * 
		 * WebElement accessibility=driver.findElement(By.id(
		 * "accessibilitySelectAccessibleIconTickTitle"));
		 * if(accessibility.isSelected()) {
		 * System.out.println("Accessibility services : "
		 * +accessibility.getText()); }else{
		 * System.out.println("Accessibility services are not opted"); }
		 * 
		 * 
		 * //4.Walk speed section System.out.println(
		 * "*********************Walk speed****************************");
		 * 
		 * WebElement
		 * walk=driver.findElement(By.name("search-options-to-from-walk-speed"
		 * )); Select walkspeed=new Select(walk);
		 * 
		 * walkspeed.selectByVisibleText("Slow");
		 * 
		 * 
		 * 
		 * //5.Trip preference section:
		 * 
		 * System.out.println("*****************Trip Prference******************"
		 * );
		 * 
		 * List<WebElement> prefer=driver.findElements(By.cssSelector(
		 * "input[name='trip-options-trip-preference']"));
		 * 
		 * 
		 * System.out.println("Preference list size:"+prefer.size());
		 * 
		 * 
		 * for(int j=0;j<prefer.size();j++){
		 * 
		 * WebElement TP=prefer.get(j);
		 * 
		 * if(TP.isSelected()){ System.out.println("Inside if loop");
		 * System.out.println(TP.getText()+" option is:"
		 * +TP.getAttribute("checked")); }
		 * 
		 * 
		 * 
		 * }
		 * 
		 * driver.findElement(By.cssSelector("button[type='button']")).click();
		 * //>>>>> when the done button of the form is clicked, it is closed,
		 * but the Travel info on the main menu tab is automatically cliked and
		 * ///>>makes it difficult to look for Go button
		 */
		WebDriverWait Go = new WebDriverWait(driver, 300);
		Go.until(
				ExpectedConditions.visibilityOf(driver.findElement(By
						.id("search-button")))).click();

		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

		// Block containing No. of Travel
		/*
		 * List<WebElement>TList=driver.findElements(By.cssSelector(
		 * "div[role='listitem']"));
		 * 
		 * System.out.println("No. of travel times avaiable:"+TList.size());
		 * for(int s=0;s<TList.size();s++){
		 * 
		 * List<WebElement> Tnames=driver.findElements(By.xpath(
		 * "//div/ul/li[@class='tripLegs ng-scope']"));
		 * System.out.println("The travel schedule: "+Tnames.size());
		 * 
		 * for(int t=0;t<Tnames.size();t++) { String
		 * Tname=driver.findElement(By.
		 * xpath("//span[@class='tp_train-route-number-label']")).getText();
		 * System.out.println(Tname); System.out.println(
		 * "**************************************************************************"
		 * );
		 */

		WebElement tpresult = driver.findElement(By
				.xpath("*//div[@class='tp-result-item-timing']"));
		//System.out.println("The travel times: " + tpresult.size());
		System.out
				.println("--------------------------------------------------------------------------");

		//for (int d = 0; d <= tpresult.size(); d++) {
			//System.out.println("The number executing now: "+d);
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

			// }
			// System.out.println("out of inner for loop");

			// System.out.println("out of all for loop");

			// }

			// Accesscible and opal fare

			List<WebElement> acc = driver
					.findElements(By
							.xpath("//li[@ng-if='tripSummaryVm.isAccessible'][@class='ng-scope']"));
			//System.out.println("size:" + acc.size());
			
			for (int a = 0; a <=acc.size(); a++) {
				System.out.println("the no now: "+a);
				String acctext = driver.findElement(
						By.xpath("//li/span[@class='sr-only']")).getText();
				System.out.println("Mobility service: " +acctext);

				String opal1 = driver.findElement(
						By.xpath("//span[@class='opal-fare ng-binding']"))
						.getText();
				System.out.println("The opal fare: " + opal1);
				System.out
						.println("*****           ************END**********            ************");

			

		}
	}
}
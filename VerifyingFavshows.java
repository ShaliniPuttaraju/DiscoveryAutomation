package stepDefinition;

import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class VerifyingFavshows {

	// Driver declaration
	WebDriver driver;
	Actions act;
	String firstvideodeTitle, FirstVideoActualDescription,
			firstvideodescription, secondvideoTitle, SecondvideoTitleCaptured,
			SecondvideodescriptionCaptured, SecondVideoActualDescription;
	WebElement firstVideo;
	JavascriptExecutor js;

	@Given("^Navigate to URL$")
	public void navigateToUrl() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"D:\\AutomationWrkSpace\\DiscoveryApp\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.discovery.com");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		Thread.sleep(25000);
	}

	@When("^Add Recommended Videoes into favourite shows$")
	public void AddVideosIntoFavoriteShows() throws InterruptedException {
		driver.findElement(By.xpath("//div[text()='Recommended']")).click();

		js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollBy(0,150)");
		Thread.sleep(6000);

		Actions act = new Actions(driver);

		WebElement firstVideo = driver
				.findElement(By
						.xpath("(//div[@class='content-box showTileSquare__contentBox'])[1]"));
		act.moveToElement(firstVideo);
		Thread.sleep(2000);

		driver.findElement(By.xpath("(//span[@class='tooltip-wrapper']//i)[1]"))
				.click();
		Thread.sleep(2000);
		WebElement ele = driver.findElement(By
				.xpath("(//div[@class='showTileSquare__description']/div)[1]"));
		firstvideodescription = ele.getText();

		System.out
				.println("**First favorite Video Description from MyRecommeded*:"
						+ firstvideodescription);

		ele = driver
				.findElement(By
						.xpath("(//div[@class='showTileSquare__description']/..)[1]/h3/div"));
		firstvideodeTitle = ele.getText();

		System.out
				.println("**First favorite video Title captured from MyRecommeded**:"
						+ firstvideodeTitle.toUpperCase());

		Thread.sleep(5000);

		// Second Video
		WebElement secondVideo = driver
				.findElement(By
						.xpath("(//div[@class='content-box showTileSquare__contentBox'])[2]"));
		act.moveToElement(secondVideo);
		driver.findElement(By.xpath("(//span[@class='tooltip-wrapper']//i)[2]"))
				.click();
		Thread.sleep(2000);
		ele = driver.findElement(By
				.xpath("(//div[@class='showTileSquare__description']/div)[2]"));
		SecondvideodescriptionCaptured = ele.getText();

		System.out
				.println("*Second favorite Description captured from MyRecommeded*:"
						+ SecondvideodescriptionCaptured);

		ele = driver
				.findElement(By
						.xpath("(//div[@class='showTileSquare__description']/..)[2]/h3/div"));
		SecondvideoTitleCaptured = ele.getText();

		System.out.println("*Second favorite Title captured from MyRecommeded:"
				+ SecondvideoTitleCaptured.toUpperCase());
		Thread.sleep(5000);
		driver.findElement(By.xpath("//i[@class='icon-menu']")).click();
		Thread.sleep(5000);

	}

	@Then("^Recommended videoes should be added into favourite shows successfully$")
	public void VerifyVideos() throws InterruptedException {
		Actions action = new Actions(driver);
		driver.findElement(By.xpath("//span[text()='My Videos']")).click();
		Thread.sleep(10000);
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,600)");
		// First Video Verification
		Thread.sleep(4000);
		WebElement FirstVideo = driver
				.findElement(By
						.xpath("(//div[@class='content-box showTileSquare__contentBox'])[1]"));
		action.moveToElement(FirstVideo);
		Thread.sleep(3000);
		String FirstvideoActualTitle = driver
				.findElement(
						By.xpath("(//div[@class='content-box showTileSquare__contentBox']//a)[1]//h3/div"))
				.getText();
		System.out.println("First video actual Title : "
				+ FirstvideoActualTitle);
		Thread.sleep(1000);
		String FirstVideoActualDescription = driver
				.findElement(
						By.xpath("(//div[@class='showTileSquare__description']/div)[1]"))
				.getText();
		System.out.println("First video Actaul Description : "
				+ FirstVideoActualDescription);
		Thread.sleep(4000);

		// Second video verification
		driver.findElement(
				By.xpath("(//i[@class='flipIconCore__icon icon-check  '])[1]"))
				.click();
		Thread.sleep(4000);
		WebElement sndvdeo = driver
				.findElement(By
						.xpath("(//div[@class='content-box showTileSquare__contentBox'])[1]"));

		action.moveToElement(sndvdeo);
		secondvideoTitle = driver
				.findElement(
						By.xpath("(//div[@class='content-box showTileSquare__contentBox']//a)[1]//h3/div"))
				.getText();
		System.out.println("Second video Actaul Title : " + secondvideoTitle);

		SecondVideoActualDescription = driver
				.findElement(
						By.xpath("(//div[@class='showTileSquare__description']/div)[1]"))
				.getText();
		System.out.println("Second video Actaul Description"
				+ SecondVideoActualDescription);
		// First Video Assertion
		System.out.println("**********First video Verification************");
		Assert.assertEquals(FirstvideoActualTitle,
				firstvideodeTitle.toUpperCase());
		System.out.println("The actual " + firstvideodeTitle
				+ "+and expected title" + firstvideodeTitle
				+ "added are verified successfully");
		Assert.assertEquals(FirstVideoActualDescription, firstvideodescription);
		System.out.println("The actual " + FirstVideoActualDescription
				+ "+and expected Description" + firstvideodescription
				+ "added are verified successfully");
		// Second Video Assertion
		System.out.println("**********Second video Verification**********");
		Assert.assertEquals(secondvideoTitle,
				SecondvideoTitleCaptured.toUpperCase());
		System.out.println("The actual " + secondvideoTitle
				+ "+and expected title"
				+ SecondvideoTitleCaptured.toUpperCase()
				+ "added are verified successfully");

		driver.close();

	}

}

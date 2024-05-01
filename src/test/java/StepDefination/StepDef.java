package StepDefination;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import PageObject.AddNewCustomer;
import PageObject.LoginPage;
import PageObject.SearchCustomerPage;
import Utitlities.ReadConfig;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StepDef extends BaseClass {
	
	/*
	 * @Before(order=1) //prioritize before method public void setup() {
	 * System.out.println("Setup method Executed");
	 * WebDriverManager.chromedriver().setup(); driver = new ChromeDriver(); }
	 */
	
	
	@Before("@Sanity")  //prioritize before method
	public void setup() throws FileNotFoundException
	{
		readConfig = new ReadConfig();
		
		//Initialized Logger
		log = LogManager.getLogger("StepDef");
		
		
		System.out.println("Setup Sanity method Executed");
		
		String browser = readConfig.getBrowser(); 
		
		switch(browser.toLowerCase())
		{
		case "chrome":
			ChromeOptions co = new ChromeOptions();
			//co.addArguments("--remote-allow-origins=*");
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(co);
			driver =new ChromeDriver();
			break;
			
		case "msedge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new EdgeDriver();
			break;	
		default:
			driver= null;
			break;
		
			
		}
	
		log.info("setup executed");
	}
	
	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {
		
		
		loginpg= new LoginPage(driver);
		addNewCustpg = new AddNewCustomer(driver);
		SearchCustPg  = new SearchCustomerPage(driver);
		
		log.info("Chrome browser launched successfully");
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		driver.get(url);
		
		
	    }

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String emailadd, String pwd) {
		
		loginpg.enterEmail(emailadd);
		
		loginpg.enterPassword(pwd);
	    	}

	@When("Click on Login")
	public void click_on_login() {
		
		loginpg.clickOnLoginButton();
	}

	
	/////////////////////Login Page /////////////// 
	@Then("Page Title should be {string}")
	public void page_title_should_be(String expectedTitle) {
		
		String actualTitle=driver.getTitle();

		if(actualTitle.equals(expectedTitle))
		{
			
			Assert.assertTrue(true);//pass
		}
		else
		{
			Assert.assertTrue(false);//fail
			log.warn("Test case failed");
		}

	}

	@When("User click on Log out link")
	public void user_click_on_log_out_link() {
		loginpg.clickOnLogOutButton();
	}

	
	  @Then("close browser") 
	  public void close_browser() 
	  { 
		  driver.close();
		  //driver.quit();
	  }
	 
/////////////////// Add new Customer ///////////////////////

@Then("User can view Dashboad")
public void user_can_view_dashboad() {
   
	
	
	  String actualTitle = addNewCustpg.getPageTitle(); 
	  String expectedTitle = "Dashboard / nopCommerce administration";
	  if(actualTitle.equals(expectedTitle)) 
	  { 
		  Assert.assertTrue(true); 
	  } 
	  else 
	  {
	  Assert.assertTrue(false);
	  log.warn("Test case failed");
	  }
	 
	 
}


@When("User click on customers Menu")
public void user_click_on_customers_menu() 

	{
	addNewCustpg.clickOnCustomersMenu();
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}

@When("click on customers Menu Item")
public void click_on_customers_menu_item() {
	addNewCustpg.clickOnCustomersMenuItem();
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

@When("click on Add new button")
public void click_on_add_new_button() {
	addNewCustpg.clickOnAddnew();
}

@Then("User can view Add new customer page")
public void user_can_view_add_new_customer_page() {

		String actualTitle = addNewCustpg.getPageTitle();
		String expectedTitle = "Add a new customer / nopCommerce administration";

		if(actualTitle.equals(expectedTitle))
		{
			Assert.assertTrue(true);//pass
		}
		else
		{
			
			Assert.assertTrue(false);//fail
			log.warn("Test case failed");
		}
}

@When("User enter customer info")
public void user_enter_customer_info() {
	addNewCustpg.enterEmail(randomemailid()+ "@gmail.com");
	addNewCustpg.enterPassword("jam_pan@we");
	addNewCustpg.enterFirstName("jasmin");
	addNewCustpg.enterLastName("josph");
	addNewCustpg.enterGender("Male");
	addNewCustpg.enterDob("6/11/1989");
	addNewCustpg.enterCompanyName("CodeStudio");
	addNewCustpg.enterAdminContent("Admin content");
	addNewCustpg.enterManagerOfVendor("Vendor 1");

}

@When("click on Save button")
public void click_on_save_button() {
	addNewCustpg.clickOnSave();
}

@Then("User can view confirmation message {string}")
public void user_can_view_confirmation_message(String exptectedConfirmationMsg) {
	String bodyTagText = driver.findElement(By.tagName("Body")).getText();
	if(bodyTagText.contains(exptectedConfirmationMsg))
	{
		Assert.assertTrue(true);//pass
		log.info(" User can view confirmation message Test case failed");
	}
	else
	{
		Assert.assertTrue(false);//fail
		log.warn(" User can view confirmation message Test case failed");
	}

}


///////////////////////////////// Search customer by email //////////////////////////////


@When("Enter customer EMail")
public void enter_customer_e_mail() {
	SearchCustPg.enterEmailAdd("uxQUn@gmail.com");
}

@When("Click on search button")
public void click_on_search_button() {
   	SearchCustPg.clickOnSearchButton();
	
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

@Then("User should found Email in the Search table")
public void user_should_found_email_in_the_search_table() {
		String expectedEmail = "uxQUn@gmail.com";
		 Assert.assertTrue(SearchCustPg.searchCustomerByEmail(expectedEmail));
		/*
		 * if( SearchCustPg.searchCustomerByEmail(expectedEmail) ==true) {
		 * Assert.assertTrue(true); } else { Assert.assertTrue(false); }
		 */
}


//////////////////////////// search customer by name ///////////////////////////////

@When("Enter customer FirstName")
public void enter_customer_first_name() throws InterruptedException {
   SearchCustPg.enterFirstName("Victoria");
   Thread.sleep(2000);
}

@When("Enter customer LastName")
public void enter_customer_last_name() {
	SearchCustPg.enterLastName("Terces");
}

@Then("User should found Name in the Search table")
public void user_should_found_name_in_the_search_table() {
  String expectedName = "Victoria Terces";
  if( SearchCustPg.searchCustomerByName(expectedName) ==true) 
  {
	  log.info("Search customer by name case paased");
	  Assert.assertTrue(true); 
  } 
  else 
  {  
	  Assert.assertTrue(false); 
	  log.warn(" Search customer by name Test case failed");
  }
}

/*
 * @AfterStep 
 * public void addscreenshot(Scenario scenario) { final byte[]
 * Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES) ;
 * scenario.attach(Screenshot, "image/png", scenario.getName());
 * 
 * }
 */

 @After
 public void teardown(Scenario sc)
 {
	System.out.println("TearDown method executed");
	if(sc.isFailed()==true)
	{
		//Convert web driver object to TakeScreenshot

		String fileWithPath = "C:\\Users\\harsh\\eclipse-workspace\\CucumberTDD\\Screenshot\\failedScreenshot.png";
		TakesScreenshot scrShot =((TakesScreenshot)driver);

		//Call getScreenshotAs method to create image file
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

		//Move image file to new destination
		File DestFile=new File(fileWithPath);

		//Copy file at destination

		try {
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	driver.quit(); 
 }


 
 
 //beforeStep and afterStep method 
	/*
	 * @BeforeStep public void BeforeStepMethodDemo() {
	 * System.out.println("This is before method"); }
	 * 
	 * @AfterStep public void afterStepMethodDemo() {
	 * System.out.println("This is after method"); }
	 */
}


package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchCustomerPage {

	WebDriver ldriver;
	public SearchCustomerPage(WebDriver rDriver )
	{
		ldriver=rDriver;

		PageFactory.initElements(rDriver, this);
	}
	
	@FindBy(id="SearchEmail")
	WebElement emailAdd;
	
	@FindBy(id="search-customers")
	WebElement searchBtn;
	
	@FindBy(xpath="//table[@id='customers-grid']//tbody/tr")
	List<WebElement> tableRows;
	
	@FindBy(id="SearchFirstName")
	WebElement firstname;
	
	@FindBy(id="SearchLastName")
	WebElement lastname;
	
	
	public void enterEmailAdd(String email) 
	{
	  	emailAdd.sendKeys(email);
	}
	
	public void clickOnSearchButton()
	{
		searchBtn.click();
		
	}
	public boolean searchCustomerByEmail(String email)
	{
		boolean found = false;

		//total no. of rows in a grid
		int tblRows = tableRows.size();


		//total no. of columns
		//int tblColumns = tableColumns.size();

		for(int i=1;i<=tblRows;i++)//to iterate all the rows of the grid
		{
			System.out.println("Searching row:" +i );

			WebElement webElementEmail = ldriver.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr[" + i  + "]/td[2]"));
			String actualEmailAdd = webElementEmail.getText();
			System.out.println(actualEmailAdd);

			if(actualEmailAdd.equals(email))
			{
				found=true;
			}

		}

		return found;

	}
	
	/////////////////// Search Customer By Name /////////////////////////////////////

	//action method to enter first Name 
	public void enterFirstName(String txtfirstName)
	{
		firstname.sendKeys(txtfirstName);
	}
	
	//action method to enter Last Name 
	public void enterLastName(String txtLastName)
	{
			lastname.sendKeys(txtLastName);
	}
	
	public boolean searchCustomerByName(String name)
	{
		boolean found = false;

		//total no. of rows in a grid
		int ttlRows = tableRows.size();


		for(int i=1;i<=ttlRows;i++)//to iterate all the rows of the grid
		{
			System.out.println("Searching row:" +i );

			WebElement webElementName = ldriver.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr[" + i  + "]/td[3]"));
			String actualEmailAdd = webElementName.getText();
			System.out.println(actualEmailAdd);

			if(actualEmailAdd.equals(name))
			{
				found=true;
				break;
			}

		}

		return found;

	}
}

package StepDefination;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;

import PageObject.AddNewCustomer;
import PageObject.LoginPage;
import PageObject.SearchCustomerPage;
import java.util.Properties;
import io.cucumber.core.logging.Logger;
import Utitlities.ReadConfig;


import org.apache.logging.log4j.*;

//Parent class
public class BaseClass {

public LoginPage loginpg;
	
	public WebDriver driver;
	
	public AddNewCustomer addNewCustpg;

	public SearchCustomerPage SearchCustPg ;
	
	public static org.apache.logging.log4j.Logger log;
	
	public ReadConfig readConfig;

	//Generate random emailId
	public String randomemailid()
	{
		String randomemail= RandomStringUtils.randomAlphabetic(5);
		return randomemail;
	 
	}
	
}

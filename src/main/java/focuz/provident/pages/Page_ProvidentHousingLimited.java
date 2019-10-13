package focuz.provident.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

import focuz.provident.wrappers.ProvidentWrapper;

public class Page_ProvidentHousingLimited extends ProvidentWrapper{

	@SuppressWarnings("static-access")
	public Page_ProvidentHousingLimited(RemoteWebDriver driver,ExtentTest extentTest){

		PageFactory.initElements(driver, this);		
		this.extentTest  =  extentTest;
	}

	//Same as WebElement ProjectsMenu = driver.findElementByXPath("//a[text()='Projects']");
	
	@FindBy(how=How.XPATH, using="//a[text()='Projects']")
	public WebElement ProjectsMenu;	

	public Page_AffordableProjects clickOnProjectsMenu(){			
		hightlightWebelement(ProjectsMenu);
		click(ProjectsMenu);		
		return new Page_AffordableProjects();		
	}

}

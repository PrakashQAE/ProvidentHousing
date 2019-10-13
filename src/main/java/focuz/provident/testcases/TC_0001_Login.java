package focuz.provident.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import focuz.provident.pages.Page_ProvidentHousingLimited;
import focuz.provident.wrappers.ProvidentWrapper;

public class TC_0001_Login extends ProvidentWrapper{
	
	
	@BeforeClass(alwaysRun=true)
	public void testDetails(){
		
		testDataSheetName = "TestData";
		testCaseID = this.getClass().getName().substring(26,34).replaceAll("[_]", "");
	}
	
	
	@Test(dataProvider="getTestData",groups = {"Sprint1","Smoke"},alwaysRun=true)
	public void TC_0001(String Test_Case_ID) throws InterruptedException{		
		 
		new Page_ProvidentHousingLimited(driver,  extentTest)
		.clickOnProjectsMenu();		
	}

}

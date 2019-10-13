package focuz.provident.wrappers;

import java.io.IOException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import focuz.provident.utilities.ConfigDetails;
import focuz.provident.utilities.TestDataReader;

public class ProvidentWrapper extends GenericWrapper{
	
	
	public String browserType;
	
	@BeforeSuite(alwaysRun=true)
	public void startReportTest(){
		//Creates an html report before test case execution
	 
		startReport();
	}
	
	@BeforeMethod(alwaysRun=true)
	public void loadTestDetails() throws IOException, InterruptedException{
		
		testCaseDescription = ConfigDetails.testCaseDesc(testDataSheetName, testCaseID);
		
		Category = ConfigDetails.category(testDataSheetName, testCaseID);
		
		Author = ConfigDetails.testerName(testDataSheetName, testCaseID);
		
		extentTest = startTestCase(testCaseID, testCaseDescription);
		
		extentTest.assignCategory(Category);
		
		extentTest.assignAuthor(Author);
	 
		launchApplication(browserType);
				
	}
	
	@AfterMethod(alwaysRun=true)
	public void closeTest(){
		
		driver.quit();
		
		
	}
	
	@AfterSuite(alwaysRun=true)
	public void flushTest(){
		
		endTestCase();
		endReportResult();
		
	}
 
	@DataProvider(name ="getTestData")
	public Object[][] getData() throws IOException{
		
		return TestDataReader.getSheetValues(testDataSheetName, testCaseID);
	}
	

}

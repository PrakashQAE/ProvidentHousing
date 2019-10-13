package focuz.provident.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public abstract class ExtentReport {

	public static RemoteWebDriver driver;
	public static ExtentReports extentReport;
	public static ExtentTest extentTest;
	public String testDataSheetName, testCaseID, testCaseDescription, Category, Author;	
	public abstract String takeScreenShotBase64();
	public abstract String takeFullPageScreenShot();

	public ExtentReports startReport(){

		Date dt = new Date();

		String currentDateAndTime = new SimpleDateFormat("dd-MM-yyyy").format(dt);

		String CurrentHourAndMinutes = new SimpleDateFormat("hh-mm").format(dt).trim();		

		extentReport = new ExtentReports("C:\\Users\\PRAKASH P\\workspace\\ProvidentHousing_AutomationSuite\\TestOutput\\myReport.html");

		extentReport.loadConfig(new File("./TestOutput/ExtentReport/extent-config.xml"));

		return extentReport;
	}

	public ExtentTest startTestCase(String testCaseID, String testCaseDescription){

		extentTest = extentReport.startTest(testCaseID,testCaseDescription);

		return extentTest;

	}

	public void reportStep(String description, String  resultStatus)  {
		
		if(resultStatus.toUpperCase().equalsIgnoreCase("PASS")){
			
			extentTest.log(LogStatus.PASS, description+extentTest.addBase64ScreenShot(takeScreenShotBase64()));
		}
		
	}
	
	public void reportStepWithFullPage(String description, String  resultStatus){
		

		if(resultStatus.toUpperCase().equalsIgnoreCase("PASS")){
			
			extentTest.log(LogStatus.PASS, description+extentTest.addScreenCapture(takeFullPageScreenShot()));
		}		
	}
	
	public void endReportResult(){

		extentReport.flush();
	}

	public void endTestCase(){

		extentReport.endTest(extentTest);
	}
}

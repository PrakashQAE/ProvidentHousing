package focuz.provident.wrappers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import focuz.provident.utilities.ExtentReport;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.shooting.ShootingStrategy;

public class GenericWrapper extends ExtentReport{

	public void launchApplication(String browserType) throws InterruptedException{

		try {
			
			FileInputStream inputTestDataFile = new FileInputStream("./TestData/"+testDataSheetName+".xlsx");

			XSSFWorkbook workBook = new XSSFWorkbook(inputTestDataFile);

			XSSFSheet workSheet  = workBook.getSheetAt(0);

			String environmentSelection = workSheet.getRow(0).getCell(1).getStringCellValue();

			XSSFRow appURL = workSheet.getRow(1);

			String applicationURL = appURL.getCell(1).getStringCellValue();

			browserType = workSheet.getRow(2).getCell(1).getStringCellValue();

			if(environmentSelection.trim().equalsIgnoreCase("QA")){

				if(browserType.equalsIgnoreCase("chrome")){

					System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");

					driver = new ChromeDriver();

					driver.manage().deleteAllCookies();	

					driver.manage().window().maximize();

					driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

					driver.navigate().to(applicationURL.trim());

					reportStep("Launched", "PASS");
					
					Thread.sleep(2000);
				}			

			}			

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	public void click(WebElement element){

		try {

			WebDriverWait wait = new WebDriverWait(driver, 15);

			wait.until(ExpectedConditions.elementToBeClickable(element));

			element.click();
			
			reportStep(element + " is Clicked Successfully", "PASS");
			
		} catch (InvalidElementStateException  e) {
			// TODO Auto-generated catch block
			
			reportStep(element+ " is invalid", "FAIL");

		} catch (NoSuchElementException  e) {
			// TODO Auto-generated catch block
			
			reportStep("No Such Element Exception "+ element , "FAIL");

		}
	}
	
	public void enter(WebElement element,String testData){
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			
			wait.until(ExpectedConditions.visibilityOf(element));
			
			element.clear();
			
			element.sendKeys(testData);
			
			reportStep(testData +" is entered on "+ element+ " successfully", "PASS");
			
		}  catch (InvalidElementStateException  e) {
			// TODO Auto-generated catch block
			
			reportStep(element+ " is invalid", "FAIL");

		} catch (NoSuchElementException  e) {
			// TODO Auto-generated catch block
			
			reportStep("No Such Element Exception "+ element , "FAIL");

		}
	}
	
	public String takeScreenShotBase64(){
		
		TakesScreenshot newScreen = driver;
		
		String screenShot = newScreen.getScreenshotAs(OutputType.BASE64);
		
		return "data:image/png;base64,"+screenShot;
	}
	
	public String takeFullPageScreenShot(){
		
		Date dt = new Date();
		
		String currentDateAndTime = new SimpleDateFormat("dd-MM-yyyy_hh mm ss").format(dt);
		
		Screenshot sourceScreenShot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		
		String destinationPath = System.getProperty(("user.dir")+"/TestOutput/ScreenShots/"+currentDateAndTime+".png");
		
		try {
			ImageIO.write(sourceScreenShot.getImage(), "PNG", new File(destinationPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return destinationPath;	
	}	
	
	public void hightlightWebelement(WebElement element){
		
		 JavascriptExecutor jsExecutor = driver;
		 
		 jsExecutor.executeScript("arguments[0].style.border='3px solid green'", element);
		
	}



}

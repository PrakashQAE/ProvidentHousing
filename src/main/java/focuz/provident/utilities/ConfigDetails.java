package focuz.provident.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ConfigDetails {

	public static String testCaseDesc(String testDataSheetName, String testCaseID) throws IOException{


		String testCaseDescription = "";

		FileInputStream inputTestDataFile = new FileInputStream("./TestData/"+testDataSheetName+".xlsx");

		XSSFWorkbook workBook = new XSSFWorkbook(inputTestDataFile);

		XSSFSheet workSheet  = workBook.getSheetAt(0);

		for(int i = 1; i < workSheet.getPhysicalNumberOfRows(); i++)
		{
			if(workSheet.getRow(i).getCell(0).getStringCellValue().equalsIgnoreCase(testCaseID)){

				testCaseDescription = workSheet.getRow(i).getCell(2).getStringCellValue();
			}
		}
		
		workBook.close();

		return testCaseDescription;
	}
	public static String category(String testDataSheetName, String testCaseID) throws IOException{


		String category = "";

		FileInputStream inputTestDataFile = new FileInputStream("./TestData/"+testDataSheetName+".xlsx");

		XSSFWorkbook workBook = new XSSFWorkbook(inputTestDataFile);

		XSSFSheet workSheet  = workBook.getSheetAt(0);
		
		category = workSheet.getRow(4).getCell(1).getStringCellValue();
		
		workBook.close();		 

		return category;
	}
	
	public static String testerName(String testDataSheetName, String testCaseID) throws IOException{


		String testerName = "";

		FileInputStream inputTestDataFile = new FileInputStream("./TestData/"+testDataSheetName+".xlsx");

		XSSFWorkbook workBook = new XSSFWorkbook(inputTestDataFile);

		XSSFSheet workSheet  = workBook.getSheetAt(0);
		
		testerName = workSheet.getRow(3).getCell(1).getStringCellValue();
		
		workBook.close();		 

		return testerName;
	}

}

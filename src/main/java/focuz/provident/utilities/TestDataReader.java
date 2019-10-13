package focuz.provident.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestDataReader {

	public static Object[][] getSheetValues(String testDataSheetName,String testCaseID) throws IOException{

		String [][] testData = null;		

		FileInputStream inputTestDataFile =  new FileInputStream("./TestData/"+testDataSheetName+".xlsx");

		XSSFWorkbook workBook = new XSSFWorkbook(inputTestDataFile);

		XSSFSheet QAworkSheet = workBook.getSheetAt(0);

		XSSFRow environmentSelectionRow = QAworkSheet.getRow(0);
		
		String environmntSelectionValue = environmentSelectionRow.getCell(1).getStringCellValue();

		if(environmntSelectionValue.trim().equalsIgnoreCase("QA")){

			XSSFSheet qaTestDataWorkSheet = workBook.getSheetAt(1);

			int totalNumberOfRowsUsedQA = qaTestDataWorkSheet.getPhysicalNumberOfRows();

			System.out.println("Total number of rows used in QA Sheet--->"+totalNumberOfRowsUsedQA);

			int testCaseRow = 1;

			for(int i=0 ; i< totalNumberOfRowsUsedQA; i++){

				XSSFRow eachRow = qaTestDataWorkSheet.getRow(i);

				System.out.println("1st Row value :"+eachRow.getCell(0).getStringCellValue());

				if(eachRow.getCell(0).getStringCellValue().equalsIgnoreCase(testCaseID)){

					XSSFRow testCaseRowNumber = qaTestDataWorkSheet.getRow(i);

					int totalNumberOfColumnsUsed = testCaseRowNumber.getLastCellNum();

					System.out.println("Total Number of Columns used in Test Case  "+totalNumberOfColumnsUsed);

					testData = new String[testCaseRow][totalNumberOfColumnsUsed];

					for (int j = 0; j < totalNumberOfColumnsUsed; j++) {

						String eachTestDataCellValue = "";

						eachTestDataCellValue = testCaseRowNumber.getCell(j).getStringCellValue();

						testData[i-i][j] = eachTestDataCellValue;

						System.out.println(testData[i-i][j]);
					}					
				}
			}			
		}

		else {

			XSSFSheet UATworkSheet = workBook.getSheetAt(2);

			int totalNumberOfRowsUsedUAT = UATworkSheet.getPhysicalNumberOfRows();

			System.out.println("Total number of rows used in QA Sheet--->"+totalNumberOfRowsUsedUAT);

			int testCaseRow = 1;
			
			for(int i = 0; i< totalNumberOfRowsUsedUAT; i++){
				
				XSSFRow eachRow = UATworkSheet.getRow(i);
				
				if (eachRow.getCell(0).getStringCellValue().equals(testCaseID)) {
					
					XSSFRow testCaseRowNumber = UATworkSheet.getRow(i);
					
					int totalNumberOfColumnsUsedUAT = testCaseRowNumber.getLastCellNum();
					
					testData = new String [testCaseRow][totalNumberOfColumnsUsedUAT];
					
					for(int j= 0; j <totalNumberOfColumnsUsedUAT; j++){
						
						String eachTestDataCellValue = "";

						eachTestDataCellValue = testCaseRowNumber.getCell(j).getStringCellValue();

						testData[i-i][j] = eachTestDataCellValue;

						System.out.println(testData[i-i][j]);
						
						
					}					
				}
			}

		}
		
		inputTestDataFile.close();
		workBook.close();

		return testData;

	}
}

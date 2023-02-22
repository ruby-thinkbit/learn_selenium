package Utilities;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {

	static String projectPath;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	
	// constructor
	public ExcelUtilities(String excelPath, String sheetName) {
		try {
			
			//workbook = new XSSFWorkbook(projectPath+"/test-input/Untitled spreadsheet.xlsx");
			workbook = new XSSFWorkbook(excelPath);
			sheet = workbook.getSheet(sheetName);
		}catch(Exception ex) {
			System.out.println("Error: "+ex.getMessage());
			System.out.println("Cause: "+ex.getCause());
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		Get_Row_Count();
		Get_Cell_Data(0,0);
	}
	
	public static int Get_Row_Count() {
		int row_count = 0;
		try {
			row_count = sheet.getPhysicalNumberOfRows();
		}catch(Exception ex) {
			System.out.println("Error: "+ex.getMessage());
			System.out.println("Cause: "+ex.getCause());
			ex.printStackTrace();
		}
		return row_count;
	}
	
	public static int Get_Col_Count() {
		int col_count = 0;
		try {
			col_count = sheet.getRow(0).getPhysicalNumberOfCells();
		}catch(Exception ex) {
			System.out.println("Error: "+ex.getMessage());
			System.out.println("Cause: "+ex.getCause());
			ex.printStackTrace();
		}
		return col_count;
	}
	
	public static String Get_Cell_Data(int row_number, int col_number) {
		String cellData = null;
		try {
			cellData = sheet.getRow(row_number).getCell(col_number).getStringCellValue();
		}catch(Exception ex) {
			System.out.println("Error: "+ex.getMessage());
			System.out.println("Cause: "+ex.getCause());
			ex.printStackTrace();
		}
		return cellData;
	}
}

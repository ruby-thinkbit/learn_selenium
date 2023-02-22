package Utilities;

public class demoExcel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String projectPath = System.getProperty("user.dir");
		ExcelUtilities excel = new ExcelUtilities(projectPath+"/test-input/Untitled spreadsheet.xlsx","Sheet1");
		excel.Get_Row_Count();
		excel.Get_Cell_Data(0, 0);
	}

}

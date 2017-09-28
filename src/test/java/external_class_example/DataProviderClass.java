package external_class_example;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.testng.annotations.DataProvider;
import reader.ExcelReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * Created by Atanas.Bogev on 28-Sep-17.
 */
public class DataProviderClass {

	@DataProvider(name="products")
	public Object[][] loginData() {
		return new ExcelReader().getExcelData("src/test/resources/products.xls","Sheet1");
	}
}

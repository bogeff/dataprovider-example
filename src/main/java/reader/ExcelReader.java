package reader;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Atanas.Bogev on 28-Sep-17.
 */
public class ExcelReader implements Readable {

    public String[][] getExcelData(String fileName, String sheetName) {
        String[][] arrayExcelData = null;
        try {
            FileInputStream fs = new FileInputStream(fileName);
            Workbook wb = Workbook.getWorkbook(fs);
            Sheet sh = wb.getSheet(sheetName);
            int totalNoOfCols = sh.getColumns();
            int totalNoOfRows = sh.getRows();
            arrayExcelData = new String[totalNoOfRows-1][totalNoOfCols];
            for (int i= 1 ; i < totalNoOfRows; i++) {//skip first line as it's always used for column headers
                for (int j=0; j < totalNoOfCols; j++) {
                    arrayExcelData[i-1][j] = sh.getCell(j, i).getContents();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        return arrayExcelData;
    }
}

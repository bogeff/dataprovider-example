package reader;

/**
 * Created by Atanas.Bogev on 28-Sep-17.
 */
public interface Readable {

    String[][] getExcelData(String fileName, String sheetName);
}

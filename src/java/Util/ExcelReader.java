
package Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class ExcelReader {

    public ArrayList<String[]> guardarexcelenarray(File excel) {
        ArrayList<String[]> arrayDatos = new ArrayList<>();
        InputStream excelStream = null;
        try {
            excelStream = new FileInputStream(excel);
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(excelStream);
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
            XSSFRow xssfRow = xssfSheet.getRow(xssfSheet.getTopRow());
            String[] datos = new String[xssfRow.getLastCellNum()];

            for (Row row : xssfSheet) {
                for (Cell cell : row) {

                    datos[cell.getColumnIndex()]
                            = (cell.getCellType() == cell.getCellType().STRING) ? cell.getStringCellValue()
                            : (cell.getCellType() == cell.getCellType().NUMERIC) ? "" + cell.getNumericCellValue()
                            : (cell.getCellType() == cell.getCellType().BOOLEAN) ? "" + cell.getBooleanCellValue()
                            : (cell.getCellType() == cell.getCellType().BLANK) ? "BLANK"
                            : (cell.getCellType() == cell.getCellType().FORMULA) ? "FORMULA"
                            : (cell.getCellType() == cell.getCellType().ERROR) ? "ERROR" : "";
                    //System.out.println(datos[cell.getColumnIndex()]);
                }
                arrayDatos.add(datos);
                datos = new String[xssfRow.getLastCellNum()];

            }

        } catch (FileNotFoundException e1) {
            return null;
        } catch (IOException e2) {
            return null;
        } finally {
            try {
                excelStream.close();
            } catch (IOException ex) {
                return null;
            }

        }

        return arrayDatos;
    }
}

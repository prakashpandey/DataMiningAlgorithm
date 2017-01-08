import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by prakash on 31/10/16.
 */
public class XlsReader
{
	public static void loadDataFromExcel(String filePath) throws IOException 
	{
	
		FileInputStream inputStream = new FileInputStream(new File(filePath));

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(1);
		Iterator<Row> iterator = firstSheet.iterator();

		while (iterator.hasNext())
		{
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			List<String> list = new ArrayList<String>();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();

				switch (cell.getCellType()) 
				{
				case Cell.CELL_TYPE_STRING:
					//System.out.print(cell.getStringCellValue());
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					//System.out.print(cell.getBooleanCellValue());
					break;
				case Cell.CELL_TYPE_NUMERIC:
					double value = cell.getNumericCellValue();
					if (value > 0)
					{
						String s = cell.getSheet().getRow(0).getCell(cell.getColumnIndex())
								.getRichStringCellValue().toString();
						list.add(s);
						
						//System.out.println(value + "||" + s);
					}
					break;
				}
				//System.out.print(" - ");
			}
			if (!list.isEmpty()) 
			{
				Utils.elementList.put(list, list.size());
				Utils.addToSupportValueMap(list);
			}
		}

		workbook.cloneSheet(1);
		inputStream.close();
	
	}
}

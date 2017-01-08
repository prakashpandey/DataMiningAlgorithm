import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;



/**
 * Created by prakash on 25/9/16.
 */
public class Main
{
	// start time of program
	private static long startTime;

	public static void main(String[] args) throws IOException
	{
		startTime = System.currentTimeMillis();

		Utils.SUPPORT_VALUE = Integer.parseInt(args[2]);
		String targetFile = args[1];
		//get data from excel
		XlsReader.loadDataFromExcel(args[0]);

		// sort Map
		Map<List<String>, Integer> sortedMap = MapUtil.sortByValue(Utils.elementList);

		//printSupportValue();
		//printQuePriority(sortedMap);

		Logic logic = new Logic();

		for(List<String> list : sortedMap.keySet())
		{
			logic.runBrain(list);
		} 


		Utils.resultSet = new HashSet<List<String>>(Utils.resultList);

		//resultSet after adding all sub-groups to resultSet
		MapUtil.addAllCombinationToResultSet();

		// passing start time to write to file total time taken by program.
		printresultToFile(targetFile, startTime);

	}


	static void printresultToFile(String targetFile, long startTm) throws UnsupportedEncodingException, FileNotFoundException, IOException
	{

		FileWriter fw = new FileWriter(targetFile, true);;
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter out = new PrintWriter(bw);

		// This is done to sort list based on size.
		List<List<String>> lists = MapUtil.sortListBasedOnSize(Utils.resultSet);
		
		for(List<String> l : lists)
		{
			out.println(l.size() + "   " + l + "\n");
		}

		long time = System.currentTimeMillis() - startTm;

		out.println("Execution Time (in millis) :   " + time + "\n");
		out.flush();
		out.close();
		System.out.println("--------------- result written successfully to : " + targetFile);

	}

	static void printSupportValue()
	{
		System.out.println("--------------Support value per List ------------\n\n\n\n\n\n\n\n\n");


		for(Map.Entry<List<String>, Integer> m : Utils.SupportValueMap.entrySet())
		{
			System.out.println(m.getValue() + " " + m.getKey());
		}
	}

	static void printQuePriority(Map<List<String>, Integer> sortedMap)
	{
		System.out.println("-------------- Quepriority to process ------------\n\n");


		for(Map.Entry<List<String>, Integer> m : sortedMap.entrySet())
		{
			System.out.println(m.getValue() + " " + m.getKey());
		}

	}


}

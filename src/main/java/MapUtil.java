import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by prakash on 8/11/16.
 */



public class MapUtil
{
	/**
	 * 
	 * @param listSet
	 * @return
	 */
	public static  List<List<String>> sortListBasedOnSize( Set<List<String>> listSet) {
		
		List<List<String>> lists = new ArrayList<List<String>>();
		
		for (List<String> list : listSet) {
			lists.add(list);
		}
		
		
		Collections.sort(lists, new Comparator<List<String>>() {

			@Override
			public int compare(List<String> o1, List<String> o2) {
				return o1.size() - o2.size();
			}
		});
		
		return lists;
	}
	
	/**
	 * 
	 * @param unsortMap
	 * @return
	 */
	public static Map<List<String>, Integer> sortByValue(Map<List<String>, Integer> unsortMap) 
	{

		// 1. Convert Map to List of Map
		List<Map.Entry<List<String>, Integer>> list =
				new LinkedList<Map.Entry<List<String>, Integer>>(unsortMap.entrySet());

		// 2. Sort list with Collections.sort(), provide a custom Comparator
		//    Try switch the o1 o2 position for a different order
		Collections.sort(list, new Comparator<Map.Entry<List<String>, Integer>>() 
				{
			
				public int compare(Map.Entry<List<String>, Integer> o1,
					Map.Entry<List<String>, Integer> o2) {

				if(o1.getValue()>o2.getValue())return -1;
				else if(o1.getValue()<o2.getValue()) return 1;
				else return 0;
			}
				});

		//System.out.println("list: "+list);
		// 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
		Map<List<String>, Integer> sortedMap = new LinkedHashMap<List<String>, Integer>();

		for (Map.Entry<List<String>, Integer> entry : list) 
		{
			sortedMap.put(entry.getKey(), entry.getValue());
		}


		return sortedMap;
	}



	public static void printResultSet()
	{
		Iterator<List<String>> it = Utils.resultSet.iterator();
		while (it.hasNext())
		{
			System.out.println("\nResult table : " + Arrays.asList(it.next()));
		}
	}

	public static void printresultList()
	{
		Iterator<List<String>> it = Utils.resultList.iterator();
		while (it.hasNext())
		{
			List<String> l = it.next();
			Utils.resultQueue.add(l);
		}
		try
		{
			addAllCombinationToResultSet();
		} catch (Exception e)
		{
			System.out.println("\n All element of resultQueue are added to result set \n");
		}

		printResultSet();
	}


	public static void addAllCombinationToResultSet()
	{

		while (!Utils.resultQueue.isEmpty())
		{
			List<String> l = Utils.resultQueue.element();
			Utils.resultSet.add(l);
			Utils.resultQueue.remove();

			for (int i=0; i<l.size(); i++)
			{
				List<String> subList = new ArrayList<String>();
				for (int j=0; j<l.size(); j++)
				{
					if (i == j)
					{
						continue;
					}
					else
					{
						subList.add(l.get(j));
					}
				}

				Utils.resultQueue.add(subList);
			}
		}
	}
}

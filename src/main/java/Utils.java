import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Created by prakash on 25/9/16.
 */
public class Utils
{
    public static int SUPPORT_VALUE;
    public static HashMap<List<String>, Integer> elementList = new HashMap<List<String>, Integer>();
    
    public static Set<List<String>> resultSet = new HashSet<List<String>>();
    
    public static List<List<String>> resultList = new ArrayList<List<String>>();
	
    public static Queue<List<String>> resultQueue = new LinkedList<List<String>>();
    
    public static HashMap<List<String>, Integer> SupportValueMap = new HashMap<List<String>, Integer>();
    
    static void addToSupportValueMap(List<String> l)
    {
    	if (SupportValueMap.containsKey(l)) 
    	{
    		int val = SupportValueMap.get(l);
    		SupportValueMap.put(l, val + 1);
		}
    	else
    	{
    		SupportValueMap.put(l, 1);
    	}
    }
   
}

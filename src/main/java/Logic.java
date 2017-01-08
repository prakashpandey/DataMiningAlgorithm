import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by prakash on 25/9/16.
 */
public class Logic
{
    Queue<List<String>> queue;
   
    public Logic()
    {
        queue = new LinkedList<List<String>>(); 

    }

    public  void runBrain(List<String> list)
    {
        boolean flag = false;
        addToQueue(list);
        
        while (!queue.isEmpty())
        {
            List<String> firstElement = queue.element();
            //System.out.println(" Element to process(1st element in queue)------>   " + Arrays.asList(firstElement) + "\n");
            
            /**
             * This means current list is not present in original list
             */
            if (Utils.SupportValueMap.get(firstElement) == null)
            {
                queue.remove();
                ////System.out.println("deleting " + Arrays.asList(firstElement) + " from list, as it is not found in the Given Data-Set\n");
                continue;
            }
            
            int SUPPORT_VALUE = Integer.parseInt(Utils.SupportValueMap.get(firstElement) + "");
            
            if (flag)
            {
                SUPPORT_VALUE++;
            }
            if (SUPPORT_VALUE < Utils.SUPPORT_VALUE)
            {
                flag = true;
                addAllCombinationtoQueue(firstElement);
                queue.remove();
                //System.out.println("Removing " + Arrays.asList(firstElement) + " from queue as it is less < then given SUPPORT_VALUE && its sub-list are added to queue..!\n");
            }
            else
            {
            	Utils.resultList.add(firstElement);
                queue.remove();
                //System.out.println("List " + Arrays.asList(firstElement) + " is added to RESULT_SET and removed from processing queue as it's  SUPPORT_Val greater > than given\n");
                continue;
            }

            //System.out.println("\n All remaining element of queue to be processed  " + Arrays.asList(queue) + "\n");
        }
    }

    void addToQueue(List<String> list)
    {
        queue.add(list);
        //System.out.println("Adding to queue to get processed : " + Arrays.asList(list) + "\n");
    }

    void addAllCombinationtoQueue(List<String> l)
    {
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
            addToQueue(subList);
        }
    }
}

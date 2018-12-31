import java.util.*;
import java.io.*;
/**
 * Imports data from file for simulation
 *
 * @author Seth Markarian
 */
public class ImportTxtFile
{
    private static List<String> list = new ArrayList<String>();
    public static ArrayList<Double> list2 = new ArrayList<Double>();
    private static ArrayList<Double> payNum = new ArrayList<Double>();
    private static int index = -1;

    /**
     * Imports times into an ArrayList and converts the times into seconds
     */
    public static void importTimes() {
        File file = new File("input.txt");
        BufferedReader reader = null;
        int i = 0;
        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            while ((text = reader.readLine()) != null) {
                if(i <= 2) {
                    payNum.add(Double.parseDouble(text));
                }
                if(i > 2) {
                    list.add(text);
                }
                i++;
            }
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
        catch (IOException e) {
            e.printStackTrace();
        } 
        try {
            reader.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
        catch (IOException e) {
            e.printStackTrace();
        } 
        for(int j = 0; j < list.size(); j++) {
            double seconds = 0;  
            String[] split = list.get(j).split(":"); 
            String[] timeOfDay = split[2].split(" ");
            try {  
                seconds += Double.parseDouble(split[0]) * 3600;  
                seconds += Double.parseDouble(split[1]) * 60;  
                seconds += Double.parseDouble(timeOfDay[0]);
                list2.add(seconds);
            } catch (Exception e) {  
                System.out.println(e);
            }  
        }
    }

    /**
     * Returns all times in an ArrayList
     * 
     * @return ArrayList of all the times
     */
    public ArrayList<Double> getTimes() {
        return list2;
    }
    
    /**
     * Returns profit from txt file
     */
    public static double getProfitFromEachCustomer() {
        return payNum.get(0);
    }
    
    /**
     * Returns cost of staffing from txt file
     * 
     * @return cost of staffing
     */
    public static double getCostOfStaffingOneCashier() {
        return payNum.get(1);
    }
    
    /**
     * Returns average time for serving from txt file
     * 
     * @return average time for serving
     */
    public static double getAverageTimeForServing() {
        return payNum.get(2);
    }
}
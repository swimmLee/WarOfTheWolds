package us.mattgreen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Main {

    private final static FileInput indata = new FileInput("the_book.csv");
    private final static SortedMap<String, Integer> map = new TreeMap<>();
    //private final static Map<String, Integer> map = new HashMap<String, Integer>();
    private final static Map<Integer, ArrayList<String>> frequency  = new HashMap<Integer, ArrayList<String>>();
    private final static List<String> byIncid = new ArrayList<>();

    
    
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        String line;
        String[] words;
        int totalWords = 0, totalLines = 0;

        while ((line = indata.fileReadLine()) != null) {
            // Remove anything that's not a letter or space
            line = line.replaceAll("[^a-zA-Z ]","")
                    .toLowerCase().trim();
           
            // Don't process lines with no characters
            if (line.isEmpty()) {
                continue;
            }
            //
            //System.out.println(line);
            totalLines++;
            // Split string over one or more spaces
            words = line.split(" +");
            
            // Look for each word in the map
            for (String word : words) {
                // This word isn't yet a key, init count to 1
                if (!map.containsKey(word)) {
                    map.put(word, 1);
                    
                }
                else {
                    // Increment count using word as key
                    map.put(word, map.get(word) + 1);
                }
            }
            
            
        } //End while
        // /*
        // Loop over entries in the map, getting each key/value pair
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
            totalWords += entry.getValue();
        }
        System.out.println("\nThe total (nonempty) Line count is: " + totalLines);
        System.out.println("The total word count is: " + totalWords);
        // */
        int i = 1;
        //    /*
        int maxValue = 1;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if(entry.getValue() > maxValue){
                maxValue = entry.getValue();
                System.out.println("new max is "+ maxValue + 
                        "  Key is " + entry.getKey());
                i++;
            }
        }
        System.out.println("different Maxima "+ i);
        
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if(!frequency.containsKey(entry.getValue())){
                frequency.put(entry.getValue(), new ArrayList<>());
            }
            else{
                frequency.get(entry.getValue(), add(entry.getKey()));
            }
        //if (!map.containsKey(word)) {
        //            map.put(word, 1);
        
//  Map<Integer, ArrayList<String>> frequency  = new HashMap<Integer, ArrayList<String>>();
        //    */
        /*for(Map.Entry<String, Integer> entry : map.entrySet()) {
            if(entry.getValue() == 1){
                byIncid.add(entry.getKey());
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }
        
        for( i = 0; i<byIncid.size(); i++){
            System.out.println(byIncid.get(i)+"  value for key = "
                + map.get(byIncid.get(i)));
            
        }
*/
    }
    
}
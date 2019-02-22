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
    //private final static Map<Integer, ArrayList<String>> frequency  = new HashMap<Integer, ArrayList<String>>();
    private final static List<String> byIncid = new ArrayList<>();
    private final static List<String> onceUsed = new ArrayList<>();

    
    
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        String line;
        String[] words;
        int totalWords = 0, totalLines = 0;
        int i = 0;

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
        
        // Loop over entries in the map, getting each key/value pair
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
            totalWords += entry.getValue();
        }
        System.out.println("\nThe total (nonempty) Line count is: " + totalLines);
        System.out.println("The total word count is: " + totalWords);

        int j = 0, find = 10;
        System.out.println("\nThe " + find + " most frequently used words are:");
        String wordOut = "";
        int currentMax = 1, prevMax = totalWords;
        for(j=0; j<find; j++){
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if(entry.getValue() >= currentMax 
                        && entry.getValue() <= prevMax 
                        && !byIncid.contains(entry.getKey()))
                {
                    currentMax = entry.getValue();
                    wordOut = entry.getKey();
                    //System.out.println("new max is "+ currentMax + 
                    //        "  Key is " + entry.getKey());
                }
            }
            prevMax = currentMax;
            byIncid.add(wordOut);
            System.out.println("\"" + byIncid.get(j) + "\" occures " + currentMax + " times");
                //System.out.println("use frequency: "+ currentMax + 
                //                " \tword (key): " + byIncid.get(j));
            currentMax = 1;
        }
        /*
        This section loops over the map to find entries with usage (getValue) =1
        and loads them into an array list for the practice.
        The second loop gets the words back out. A bit redundant but instructive.
        */
        int usage = 1;
        j=0; i=0;
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            if(entry.getValue() == usage){
                onceUsed.add(entry.getKey());
                //System.out.println("frequency: "+entry.getValue()+"  word: "+ entry.getKey());
                i++;
            }
        }
        System.out.println("\n\nThere are "+ i +" words used "+ usage +" time.");
        
        
        for(String wrd : onceUsed){
            System.out.println(onceUsed.get(j));
            j++;
        }
        
        
        System.out.println("there are "+ i +" words used "+ usage +" time.");
    }
    
}